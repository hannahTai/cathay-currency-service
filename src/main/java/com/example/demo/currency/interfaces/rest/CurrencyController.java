package com.example.demo.currency.interfaces.rest;

import com.example.demo.currency.application.external.BpiExternalService;
import com.example.demo.currency.application.external.dto.CurrentPriceResponse;
import com.example.demo.currency.application.internal.CurrencyService;
import com.example.demo.currency.domain.input.CurrencyInput;
import com.example.demo.currency.domain.output.CurrencyOutput;
import com.example.demo.currency.interfaces.dto.*;
import com.example.demo.currency.interfaces.mapper.CurrencyRestMapper;
import com.example.demo.currency.interfaces.mapper.CurrencyRestTransform;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/currency")
public class CurrencyController {

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private BpiExternalService bpiExternalService;

    @PostMapping("/")
    public ResponseEntity<CurrencyAddResponse> add(@RequestBody CurrencyAddRequest request) {
        CurrencyInput input = CurrencyRestMapper.INSTANCE.toCurrencyInput(request);
        Long currencyId = currencyService.add(input);
        CurrencyAddResponse response = CurrencyRestMapper.INSTANCE.toCurrencyAddResponse(currencyId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{currencyId}")
    public ResponseEntity<Map<String, String>> modify(@PathVariable("currencyId") Long currencyId, @RequestBody CurrencyModifyRequest request) {
        CurrencyInput input = CurrencyRestMapper.INSTANCE.toCurrencyInput(currencyId, request);
        currencyService.modify(input);
        return ResponseEntity.ok(Collections.emptyMap());
    }

    @DeleteMapping("/{currencyId}")
    public ResponseEntity<Map<String, String>> remove(@PathVariable("currencyId") Long currencyId) {
        currencyService.remove(currencyId);
        return ResponseEntity.ok(Collections.emptyMap());
    }

    @GetMapping("/{currencyId}")
    public ResponseEntity<CurrencyGetOneResponse> getOne(@PathVariable("currencyId") Long currencyId) {
        CurrencyOutput output = currencyService.getOne(currencyId);
        CurrencyGetOneResponse response = CurrencyRestMapper.INSTANCE.toCurrencyGetOneResponse(output);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<CurrencyGetAllResponse> getAll() {
        List<CurrencyOutput> outputList = currencyService.getAll();
        List<CurrencyGetAllResponse.Currency> results = outputList.stream().map(CurrencyRestMapper.INSTANCE::toCurrencyGetAllResponseCurrency).collect(Collectors.toList());
        CurrencyGetAllResponse response = new CurrencyGetAllResponse();
        response.setCurrencies(results);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/current-price")
    public ResponseEntity<CurrencyGetCurrentPriceResponse> getCurrentPrice() {
        CurrentPriceResponse currentPriceResponse = bpiExternalService.currentPrice();
        List<CurrencyOutput> currencyOutputList = currencyService.getAll();
        CurrencyGetCurrentPriceResponse response = CurrencyRestTransform.toCurrencyGetCurrentPriceResponse(currentPriceResponse, currencyOutputList);
        return ResponseEntity.ok(response);
    }
}
