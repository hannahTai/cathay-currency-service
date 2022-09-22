package com.example.demo.currency.application.internal.mapper;

import com.example.demo.currency.domain.input.CurrencyInput;
import com.example.demo.currency.domain.model.Currency;
import com.example.demo.currency.domain.output.CurrencyOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyEntityMapper {
    CurrencyEntityMapper INSTANCE = Mappers.getMapper(CurrencyEntityMapper.class);

    CurrencyOutput toCurrencyOutput(Currency currency);

    Currency toCurrency(CurrencyInput currencyInput);
}
