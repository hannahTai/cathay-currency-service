package com.example.demo.currency.application.external;

import com.example.demo.currency.application.external.dto.CurrentPriceResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class BpiExternalService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public CurrentPriceResponse currentPrice() {
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        ResponseEntity<String> responseResponseEntity = restTemplate.getForEntity(url, String.class);
        String responseStr = responseResponseEntity.getBody();
        try {
            return objectMapper.readValue(responseStr, CurrentPriceResponse.class);
        } catch (JsonProcessingException e) {
            log.error("objectMapper readValue error: {}", e.getMessage());
        }
        return null;
    }

}
