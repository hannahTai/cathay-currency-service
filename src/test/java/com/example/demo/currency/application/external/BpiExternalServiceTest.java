package com.example.demo.currency.application.external;

import com.example.demo.currency.application.external.dto.CurrentPriceResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class BpiExternalServiceTest {

    @Autowired
    private BpiExternalService bpiExternalService;

    @Test
    public void currentPrice_success() {
        CurrentPriceResponse response = bpiExternalService.currentPrice();
        log.info("BpiExternalServiceTest currentPrice_success response: {}", response);
    }

}
