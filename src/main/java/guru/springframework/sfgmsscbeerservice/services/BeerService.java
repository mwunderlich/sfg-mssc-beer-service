package guru.springframework.sfgmsscbeerservice.services;

import guru.springframework.sfgmsscbeerservice.exceptions.NotFoundException;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getById(UUID beerId) throws NotFoundException;

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws NotFoundException;

    void deleteBeerById(UUID beerId);
}
