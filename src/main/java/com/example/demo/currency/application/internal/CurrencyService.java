package com.example.demo.currency.application.internal;

import com.example.demo.currency.application.internal.mapper.CurrencyEntityMapper;
import com.example.demo.currency.domain.input.CurrencyInput;
import com.example.demo.currency.domain.model.Currency;
import com.example.demo.currency.domain.output.CurrencyOutput;
import com.example.demo.currency.infrastructure.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public Long add(CurrencyInput currencyInput) {
        Currency currency = CurrencyEntityMapper.INSTANCE.toCurrency(currencyInput);
        currencyRepository.save(currency);
        return currency.getCurrencyId();
    }

    public void modify(CurrencyInput currencyInput) {
        Currency currency = currencyRepository.findById(currencyInput.getCurrencyId()).orElseThrow();
        currency.setCurrencyCode(currencyInput.getCurrencyCode());
        currency.setChineseName(currencyInput.getChineseName());
        currencyRepository.save(currency);
    }

    public void remove(Long currencyId) {
        Currency currency = currencyRepository.findById(currencyId).orElseThrow();
        currencyRepository.delete(currency);
    }

    public CurrencyOutput getOne(Long currencyId) {
        return currencyRepository.findById(currencyId).map(CurrencyEntityMapper.INSTANCE::toCurrencyOutput).orElseThrow();
    }

    public List<CurrencyOutput> getAll() {
        List<Currency> currencyList = currencyRepository.findAll();
        return currencyList.stream().map(CurrencyEntityMapper.INSTANCE::toCurrencyOutput).collect(Collectors.toList());
    }
}
