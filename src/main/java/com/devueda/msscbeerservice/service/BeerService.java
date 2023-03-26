package com.devueda.msscbeerservice.service;

import com.devueda.msscbeerservice.web.model.BeerDto;
import com.devueda.msscbeerservice.web.model.BeerPagedList;
import com.devueda.msscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId);
    BeerDto saveNewBeer(BeerDto beerDto);
    BeerDto update(UUID beerId, BeerDto beerDto);
    void deleteById(UUID beerId);

    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest of);
}
