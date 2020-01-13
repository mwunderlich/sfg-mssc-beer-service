package guru.springframework.sfgmsscbeerservice.services;

import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Created by jt on 2019-06-06.
 */
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    @Override
    public BeerDto getById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        return null;
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
        BeerDto beer = getById(beerId);

        return beer;
    }

    @Override
    public void deleteBeerById(UUID beerId) {

    }
}
