package com.example.demo.currency.interfaces.mapper;

import com.example.demo.currency.application.external.dto.CurrentPriceResponse;
import com.example.demo.currency.domain.output.CurrencyOutput;
import com.example.demo.currency.interfaces.dto.CurrencyGetCurrentPriceResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CurrencyRestTransform {

    public static CurrencyGetCurrentPriceResponse toCurrencyGetCurrentPriceResponse(CurrentPriceResponse currentPriceResponse, List<CurrencyOutput> currencyOutputList) {
        Map<String, CurrencyOutput> currencyOutputMap = currencyOutputList.stream().collect(Collectors.toMap(CurrencyOutput::getCurrencyCode, Function.identity()));

        LocalDateTime updatedDate = Optional.ofNullable(currentPriceResponse).map(CurrentPriceResponse::getTime).map(CurrentPriceResponse.CurrentPriceTime::getUpdatedISO)
                .map(updatedISO -> LocalDateTime.parse(updatedISO, DateTimeFormatter.ISO_OFFSET_DATE_TIME)).orElse(null);

        Map<String, CurrentPriceResponse.CurrentPriceBpi> externalBpiMap = Optional.of(currentPriceResponse).map(CurrentPriceResponse::getBpi).orElse(Collections.emptyMap());
        Map<String, CurrencyGetCurrentPriceResponse.CurrentPriceBpi> bpiMap = externalBpiMap.entrySet().stream().map(externalBpi -> {
            String code = externalBpi.getKey();
            Float rateFloat = externalBpi.getValue().getRateFloat();
            String chineseName = Optional.ofNullable(currencyOutputMap.get(code)).map(CurrencyOutput::getChineseName).orElse(null);

            CurrencyGetCurrentPriceResponse.CurrentPriceBpi responseBpi = new CurrencyGetCurrentPriceResponse.CurrentPriceBpi();
            responseBpi.setCode(code);
            responseBpi.setChineseName(chineseName);
            responseBpi.setRateFloat(rateFloat);
            return responseBpi;
        }).collect(Collectors.toMap(CurrencyGetCurrentPriceResponse.CurrentPriceBpi::getCode, Function.identity()));

        CurrencyGetCurrentPriceResponse response = new CurrencyGetCurrentPriceResponse();
        response.setUpdatedDate(updatedDate);
        response.setBpi(bpiMap);
        return response;
    }
}
