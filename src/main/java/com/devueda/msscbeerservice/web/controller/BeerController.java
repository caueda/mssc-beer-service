package com.devueda.msscbeerservice.web.controller;

import com.devueda.msscbeerservice.web.model.BeerDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {
        return ResponseEntity.ok(BeerDto.builder().build());
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@RequestBody BeerDto beerDto) {
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{beerId}")
                .buildAndExpand(1L)
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateByBeerId(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity deleteBeerById(@PathVariable("beerId") UUID beerId) {
        return ResponseEntity.noContent().build();
    }
}