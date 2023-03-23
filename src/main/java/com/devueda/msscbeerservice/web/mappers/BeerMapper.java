package com.devueda.msscbeerservice.web.mappers;

import com.devueda.msscbeerservice.domain.Beer;
import com.devueda.msscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses=DateMapper.class)
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto beerDto);
}
