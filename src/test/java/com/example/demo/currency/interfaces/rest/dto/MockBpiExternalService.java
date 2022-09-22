package com.example.demo.currency.interfaces.rest.dto;

import com.example.demo.currency.application.external.dto.CurrentPriceResponse;

import java.util.HashMap;
import java.util.Map;

public class MockBpiExternalService {

    public static CurrentPriceResponse getCurrentPriceResponseSuccess() {
        CurrentPriceResponse.CurrentPriceTime currentPriceTime = new CurrentPriceResponse.CurrentPriceTime();
        currentPriceTime.setUpdated("Sep 22, 2022 11:26:00 UTC");
        currentPriceTime.setUpdatedISO("2022-09-22T11:26:00+00:00");
        currentPriceTime.setUpdateduk("Sep 22, 2022 at 12:26 BST");

        CurrentPriceResponse.CurrentPriceBpi currentPriceBpiUSD = new CurrentPriceResponse.CurrentPriceBpi();
        currentPriceBpiUSD.setCode("USD");
        currentPriceBpiUSD.setSymbol("&#36;");
        currentPriceBpiUSD.setRate("19,107.2574");
        currentPriceBpiUSD.setDescription("United States Dollar");
        currentPriceBpiUSD.setRateFloat(19107.2574f);

        Map<String, CurrentPriceResponse.CurrentPriceBpi> currentPriceBpiMap = new HashMap<>();
        currentPriceBpiMap.put("USD", currentPriceBpiUSD);

        CurrentPriceResponse response = new CurrentPriceResponse();
        response.setTime(currentPriceTime);
        response.setDisclaimer("This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org");
        response.setChartName("Bitcoin");
        response.setBpi(currentPriceBpiMap);
        return response;
    }
}
