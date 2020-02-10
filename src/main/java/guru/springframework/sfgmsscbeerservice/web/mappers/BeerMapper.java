package guru.springframework.sfgmsscbeerservice.web.mappers;

import guru.springframework.sfgmsscbeerservice.domain.Beer;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto beerToBeerDto(Beer beer);
    Beer beerdtoToBeer(BeerDto beerDto);
}
