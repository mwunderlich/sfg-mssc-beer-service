package guru.springframework.sfgmsscbeerservice.services;

import guru.springframework.sfgmsscbeerservice.exceptions.NotFoundException;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import guru.springframework.sfgmsscbeerservice.web.model.BeerPagedList;
import guru.springframework.sfgmsscbeerservice.web.model.BeerStyleEnum;
import org.springframework.data.domain.PageRequest;

import java.util.UUID;

public interface BeerService {
    BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest);

    BeerDto getById(UUID beerId) throws NotFoundException;

    BeerDto saveNewBeer(BeerDto beerDto);

    BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws NotFoundException;

    void deleteBeerById(UUID beerId);
}
