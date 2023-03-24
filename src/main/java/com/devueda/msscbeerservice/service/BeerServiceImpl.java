package com.devueda.msscbeerservice.service;

import com.devueda.msscbeerservice.repository.BeerRepository;
import com.devueda.msscbeerservice.web.mappers.BeerMapper;
import com.devueda.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) {
        return beerRepository.findById(beerId)
                .map(beerMapper::beerToBeerDto)
                .orElseThrow(() -> new NofFoundException("Beer not found."));
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        var savedBeer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
        return beerMapper.beerToBeerDto(savedBeer);
    }

    @Override
    public BeerDto update(UUID beerId, BeerDto beerDto) {
        var beer = beerRepository.findById(beerId)
                .orElseThrow(() -> new NofFoundException("Beer not found."));

        beer.setBeerName(beerDto.getBeerName());
        beer.setBeerStyle(beerDto.getBeerStyleEnum().name());
        beer.setPrice(beerDto.getPrice());
        beer.setUpc(beerDto.getUpc());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteById(UUID beerId) {
        beerRepository.deleteById(beerId);
    }
}
