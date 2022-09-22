package com.example.demo.currency.interfaces.dto;

import lombok.Data;

import java.util.List;

@Data
public class CurrencyGetAllResponse {
    private List<Currency> currencies;

    @Data
    public static class Currency {
        private Long currencyId;
        private String currencyCode;
        private String chineseName;
    }
}
