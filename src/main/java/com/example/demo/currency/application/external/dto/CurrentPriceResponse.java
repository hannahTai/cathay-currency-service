package com.example.demo.currency.application.external.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class CurrentPriceResponse {
    private CurrentPriceTime time;
    private String disclaimer;
    private String chartName;
    private Map<String, CurrentPriceBpi> bpi;

    @Data
    public static class CurrentPriceTime {
        private String updated;
        private String updatedISO;
        private String updateduk;
    }

    @Data
    public static class CurrentPriceBpi {
        private String code;
        private String symbol;
        private String rate;
        private String description;
        @JsonProperty("rate_float")
        private Float rateFloat;
    }
}
