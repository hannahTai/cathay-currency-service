package com.example.demo.currency.interfaces.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class CurrencyGetCurrentPriceResponse {
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime updatedDate;
    private Map<String, CurrentPriceBpi> bpi;

    @Data
    public static class CurrentPriceBpi {
        private String code;
        private String chineseName;
        private Float rateFloat;
    }
}
