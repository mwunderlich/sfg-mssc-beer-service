package guru.springframework.sfgmsscbeerservice.services;

import guru.springframework.sfgmsscbeerservice.domain.Beer;
import guru.springframework.sfgmsscbeerservice.exceptions.NotFoundException;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import guru.springframework.sfgmsscbeerservice.web.mappers.BeerMapper;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Override
    public BeerDto getById(UUID beerId) throws NotFoundException {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow( NotFoundException::new )
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer beerToSave = beerMapper.beerdtoToBeer(beerDto);
        return beerMapper.beerToBeerDto(beerRepository.save(beerToSave));
    }

    @Override
    public BeerDto updateBeer(UUID beerId, BeerDto beerDto) throws NotFoundException {
        Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);

        beer.setBeerName(beerDto.getBeerName());
        beer.setPrice(beerDto.getPrice());

        return beerMapper.beerToBeerDto(beerRepository.save(beer));
    }

    @Override
    public void deleteBeerById(UUID beerId) {

    }
}
