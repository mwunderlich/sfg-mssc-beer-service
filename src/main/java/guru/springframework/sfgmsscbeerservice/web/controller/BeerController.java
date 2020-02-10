/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.web.controller;

import guru.springframework.sfgmsscbeerservice.services.BeerService;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Controller
 *
 * @author Martin Wunderlich
 */
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId) {

        // TODO Impl
        return new ResponseEntity<BeerDto>(BeerDto.builder().build(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {

        // TODO Impl
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) {

        // TODO Impl
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
        beerService.deleteBeerById(beerId);
    }

//    @GetMapping(path = {"beer/{beerId}"}, produces = { "application/json" })
//    public ResponseEntity<BeerDto>  getBeerById(@PathVariable("beerId") UUID beerId,
//                                                @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand){
//
//        log.debug("Get Request for BeerId: " + beerId);
//
//        if (showInventoryOnHand == null) {
//            showInventoryOnHand = false;
//        }
//
//        return new ResponseEntity<>(beerService.findBeerById(beerId, showInventoryOnHand), HttpStatus.OK);
//    }
//
//    @PostMapping(path = "beer")
//    public ResponseEntity saveNewBeer(@Valid @RequestBody BeerDto beerDto){
//
//        BeerDto savedDto = beerService.saveBeer(beerDto);
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//
//        //todo hostname for uri
//        httpHeaders.add("Location", "/api/v1/beer_service/" + savedDto.getId().toString());
//
//        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
//    }
//
//    @PutMapping(path = {"beer/{beerId}"}, produces = { "application/json" })
//    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto){
//
//        beerService.updateBeer(beerId, beerDto);
//
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//
//    @DeleteMapping({"beer/{beerId}"})
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteBeer(@PathVariable("beerId") UUID beerId){
//        beerService.deleteById(beerId);
//    }
}
