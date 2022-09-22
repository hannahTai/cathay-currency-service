package com.example.demo.currency.interfaces.dto;

import lombok.Data;

@Data
public class CurrencyGetOneResponse {
    private Long currencyId;
    private String currencyCode;
    private String chineseName;
}
