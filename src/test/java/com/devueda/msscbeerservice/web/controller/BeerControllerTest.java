package com.devueda.msscbeerservice.web.controller;

import com.devueda.msscbeerservice.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
    private static final String BEER_URI = "/api/v1/beer";
    private static final String mockBeerId = "86c41d00-7bd7-4301-a9ec-00151a4b5351";
    @Autowired
    MockMvc mockMvc;

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
        var beerDto = BeerDto.builder()
                .id(UUID.fromString(mockBeerId))
                .build();
        mockMvc.perform(post(BEER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(beerDto)))
                .andExpect(header().string("Location", "http://localhost/api/v1/beer/86c41d00-7bd7-4301-a9ec-00151a4b5351"))
                .andExpect(status().isCreated());

    }

    @Test
    void updateByBeerId() throws Exception {
        var beerDto = BeerDto.builder()
                .build();
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
}