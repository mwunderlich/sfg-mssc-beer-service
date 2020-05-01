/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.events;

import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;

/**
 * New inventory
 *
 * @author Martin Wunderlich
 */
public class NewInventoryEvent extends BeerEvent {

    public NewInventoryEvent(BeerDto beerDto) {
        super(beerDto);
    }
}
