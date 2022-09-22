package com.example.demo.currency.interfaces.dto;

import lombok.Data;

@Data
public class CurrencyModifyRequest {
    private String currencyCode;
    private String chineseName;
}
