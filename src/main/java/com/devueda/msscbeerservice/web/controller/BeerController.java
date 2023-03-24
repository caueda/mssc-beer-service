package com.devueda.msscbeerservice.web.controller;

import com.devueda.msscbeerservice.service.BeerService;
import com.devueda.msscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        var beer = beerService.getById(beerId);
        return ResponseEntity.ok(beer);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {
        var savedBeerDto = beerService.saveNewBeer(beerDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{beerId}")
                .buildAndExpand(savedBeerDto.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateByBeerId(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {
        beerService.update(beerId, beerDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId) {
        beerService.deleteById(beerId);
        return ResponseEntity.noContent().build();
    }
}