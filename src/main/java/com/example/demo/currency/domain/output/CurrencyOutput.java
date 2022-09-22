package com.example.demo.currency.domain.output;

import lombok.Data;

@Data
public class CurrencyOutput {
    private Long currencyId;
    private String currencyCode;
    private String chineseName;
}
