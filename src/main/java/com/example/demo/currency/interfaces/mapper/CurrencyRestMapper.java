package com.example.demo.currency.interfaces.mapper;

import com.example.demo.currency.domain.input.CurrencyInput;
import com.example.demo.currency.domain.output.CurrencyOutput;
import com.example.demo.currency.interfaces.dto.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CurrencyRestMapper {
    CurrencyRestMapper INSTANCE = Mappers.getMapper(CurrencyRestMapper.class);

    CurrencyInput toCurrencyInput(CurrencyAddRequest currencyAddRequest);

    CurrencyAddResponse toCurrencyAddResponse(Long currencyId);

    CurrencyInput toCurrencyInput(Long currencyId, CurrencyModifyRequest currencyModifyRequest);

    CurrencyGetAllResponse.Currency toCurrencyGetAllResponseCurrency(CurrencyOutput currencyOutput);

    CurrencyGetOneResponse toCurrencyGetOneResponse(CurrencyOutput currencyOutput);
}