package com.example.demo.currency.domain.input;

import lombok.Data;

@Data
public class CurrencyInput {
    private Long currencyId;
    private String currencyCode;
    private String chineseName;
}
