/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.web.controller;

import guru.springframework.sfgmsscbeerservice.exceptions.NotFoundException;
import guru.springframework.sfgmsscbeerservice.services.BeerService;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import guru.springframework.sfgmsscbeerservice.web.model.BeerPagedList;
import guru.springframework.sfgmsscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Controller
 *
 * @author Martin Wunderlich
 */
@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    private final BeerService beerService;

    @GetMapping(produces = { "application/json" }, path = "beer")
    public ResponseEntity<BeerPagedList> listBeers(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                   @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                   @RequestParam(value = "beerName", required = false) String beerName,
                                                   @RequestParam(value = "beerStyle", required = false) BeerStyleEnum beerStyle,
                                                   @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        BeerPagedList beerList = beerService.listBeers(beerName, beerStyle, PageRequest.of(pageNumber, pageSize), showInventoryOnHand);

        return new ResponseEntity<>(beerList, HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable("beerId") UUID beerId,
                                               @RequestParam(value = "showInventoryOnHand", required = false) Boolean showInventoryOnHand) throws NotFoundException {

        if (showInventoryOnHand == null) {
            showInventoryOnHand = false;
        }

        return new ResponseEntity<>(beerService.getById(beerId, showInventoryOnHand), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(@Validated @RequestBody BeerDto beerDto) {

        return new ResponseEntity<>(beerService.saveNewBeer(beerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity updateBeer(@PathVariable("beerId") UUID beerId, @Validated @RequestBody BeerDto beerDto) throws NotFoundException {

        return new ResponseEntity<>(beerService.updateBeer(beerId, beerDto), HttpStatus.NO_CONTENT);
    }
//
//    @DeleteMapping("/{beerId}")
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public void deleteBeer(@PathVariable("beerId") UUID beerId) {
//        beerService.deleteBeerById(beerId);
//    }

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
