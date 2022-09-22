package com.example.demo.currency.interfaces.dto;

import lombok.Data;

@Data
public class CurrencyAddRequest {
    private String currencyCode;
    private String chineseName;
}
