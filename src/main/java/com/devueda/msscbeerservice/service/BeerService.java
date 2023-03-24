package com.devueda.msscbeerservice.service;

import com.devueda.msscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);
    void deleteById(UUID beerId);
}
