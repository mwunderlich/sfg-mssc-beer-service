/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.events;

import guru.sfg.brewery.events.BeerDto;
import guru.sfg.brewery.events.BeerEvent;
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
