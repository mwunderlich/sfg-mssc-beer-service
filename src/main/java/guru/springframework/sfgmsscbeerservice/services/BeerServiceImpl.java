package guru.springframework.sfgmsscbeerservice.services;

import guru.springframework.sfgmsscbeerservice.domain.Beer;
import guru.springframework.sfgmsscbeerservice.exceptions.NotFoundException;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import guru.springframework.sfgmsscbeerservice.web.mappers.BeerMapper;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import guru.springframework.sfgmsscbeerservice.web.model.BeerPagedList;
import guru.springframework.sfgmsscbeerservice.web.model.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service("beerService")
public class BeerServiceImpl implements BeerService {

    private final BeerRepository beerRepository;
    private final BeerMapper beerMapper;

    @Cacheable(cacheNames = "beerListCache", condition = "#showInventoryOnHand == false ")
    @Override
//    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest, Boolean showInventoryOnHand) {
    public BeerPagedList listBeers(String beerName, BeerStyleEnum beerStyle, PageRequest pageRequest) {

        BeerPagedList beerPagedList;
        Page<Beer> beerPage;

        if (!StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search both
            beerPage = beerRepository.findAllByBeerNameAndBeerStyle(beerName, beerStyle, pageRequest);
        } else if (!StringUtils.isEmpty(beerName) && StringUtils.isEmpty(beerStyle)) {
            //search beer_service name
            beerPage = beerRepository.findAllByBeerName(beerName, pageRequest);
        } else if (StringUtils.isEmpty(beerName) && !StringUtils.isEmpty(beerStyle)) {
            //search beer_service style
            beerPage = beerRepository.findAllByBeerStyle(beerStyle, pageRequest);
        } else {
            beerPage = beerRepository.findAll(pageRequest);
        }

//        if (showInventoryOnHand){
//            beerPagedList = new BeerPagedList(beerPage
//                    .getContent()
//                    .stream()
//                    .map(beerMapper::beerToBeerDtoWithInventory)
//                    .collect(Collectors.toList()),
//                    PageRequest
//                            .of(beerPage.getPageable().getPageNumber(),
//                                    beerPage.getPageable().getPageSize()),
//                    beerPage.getTotalElements());
//        } else {
            beerPagedList = new BeerPagedList(beerPage
                    .getContent()
                    .stream()
                    .map(beerMapper::beerToBeerDto)
                    .collect(Collectors.toList()),
                    PageRequest
                            .of(beerPage.getPageable().getPageNumber(),
                                    beerPage.getPageable().getPageSize()),
                    beerPage.getTotalElements());
//        }

        return beerPagedList;
    }

    @Override
    public BeerDto getById(UUID beerId) throws NotFoundException {
        return beerMapper.beerToBeerDto(
                beerRepository.findById(beerId).orElseThrow( NotFoundException::new )
        );
    }

    @Override
    public BeerDto saveNewBeer(BeerDto beerDto) {
        Beer beerToSave = beerMapper.beerDtoToBeer(beerDto);
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
