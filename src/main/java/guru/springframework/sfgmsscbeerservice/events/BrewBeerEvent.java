/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.events;

import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import lombok.NoArgsConstructor;

/**
 * Brew beeer event
 *
 * @author Martin Wunderlich
 */
@NoArgsConstructor
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
