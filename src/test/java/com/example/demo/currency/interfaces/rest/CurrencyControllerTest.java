package com.example.demo.currency.interfaces.rest;

import com.example.demo.currency.application.external.BpiExternalService;
import com.example.demo.currency.interfaces.dto.CurrencyAddRequest;
import com.example.demo.currency.interfaces.dto.CurrencyModifyRequest;
import com.example.demo.currency.interfaces.rest.dto.MockBpiExternalService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.anEmptyMap;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class CurrencyControllerTest {

    private static final String PATH = "/v1/currency/";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BpiExternalService bpiExternalService;

    @Test
    public void add_success() throws Exception {
        CurrencyAddRequest request = new CurrencyAddRequest();
        request.setCurrencyCode("GBP");
        request.setChineseName("英鎊");

        String content = objectMapper.writeValueAsString(request);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(PATH).content(content)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyId", notNullValue()))
                .andReturn();

        log.info("CurrencyControllerTest add_success response: {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void modify_success() throws Exception {
        Long currencyId = 1l;

        CurrencyModifyRequest request = new CurrencyModifyRequest();
        request.setCurrencyCode("USDC");
        request.setChineseName("美元穩定幣");

        String content = objectMapper.writeValueAsString(request);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.put(PATH + "/" + currencyId).content(content)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", anEmptyMap()))
                .andReturn();

        log.info("CurrencyControllerTest modify_success response: {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void remove_success() throws Exception {
        Long currencyId = 2l;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.delete(PATH + "/" + currencyId)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", anEmptyMap()))
                .andReturn();

        log.info("CurrencyControllerTest remove_success response: {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getOne_success() throws Exception {
        Long currencyId = 1l;

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + currencyId)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencyId", notNullValue()))
                .andReturn();

        log.info("CurrencyControllerTest getOne_success response: {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getAll_success() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(PATH)
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currencies", notNullValue()))
                .andReturn();

        log.info("CurrencyControllerTest getAll_success response: {}", mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void getCurrentPrice_success() throws Exception {
        given(bpiExternalService.currentPrice()).willReturn(MockBpiExternalService.getCurrentPriceResponseSuccess());

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(PATH + "/" + "current-price")
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.updatedDate", notNullValue()))
                .andExpect(jsonPath("$.bpi", notNullValue()))
                .andReturn();

        log.info("CurrencyControllerTest getCurrentPrice_success response: {}", mvcResult.getResponse().getContentAsString());
    }
}
