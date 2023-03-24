package com.devueda.msscbeerservice.web.controller;

import com.devueda.msscbeerservice.service.BeerService;
import com.devueda.msscbeerservice.web.model.BeerDto;
import com.devueda.msscbeerservice.web.model.BeerStyleEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
    private static final String BEER_URI = "/api/v1/beer";
    private static final String mockBeerId = "86c41d00-7bd7-4301-a9ec-00151a4b5351";
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;

    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        this.objectMapper = new ObjectMapper();
    }

    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get(BEER_URI + "/{beerId}", mockBeerId))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        var beerDto = getValidBeerDto();
        when(beerService.saveNewBeer(Mockito.isA(BeerDto.class)))
                .thenReturn(beerDto);
        mockMvc.perform(post(BEER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDto)))
//                .andExpect(header().string("Location", "http://localhost/api/v1/beer/86c41d00-7bd7-4301-a9ec-00151a4b5351"))
                .andExpect(status().isCreated());

    }

    @Test
    void updateByBeerId() throws Exception {
        var beerDto = getValidBeerDto();
        mockMvc.perform(put(BEER_URI + "/{beerId}", mockBeerId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteBeerById() throws Exception {
        mockMvc.perform(delete(BEER_URI + "/{beerId}", mockBeerId))
                .andExpect(status().isNoContent());
    }

    BeerDto getValidBeerDto() {
        return BeerDto.builder()
                .beerName("Stella")
                .upc(10000L)
                .quantityOnHand(10)
                .beerStyleEnum(BeerStyleEnum.ALE)
                .price(BigDecimal.valueOf(12.99))
                .build();
    }
}