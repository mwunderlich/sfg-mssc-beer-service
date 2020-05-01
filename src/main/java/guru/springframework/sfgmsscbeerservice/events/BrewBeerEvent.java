/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.events;

import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;

/**
 * Brew beeer event
 *
 * @author Martin Wunderlich
 */
public class BrewBeerEvent extends BeerEvent {

    public BrewBeerEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
