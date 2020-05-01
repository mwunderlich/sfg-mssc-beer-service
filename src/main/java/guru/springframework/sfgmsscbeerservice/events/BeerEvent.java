/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.events;

import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * beer event
 *
 * @author Martin Wunderlich
 */
@Data
@RequiredArgsConstructor
@Builder
public class BeerEvent {

    static final long serialVersionUID = 5333452867634730824L;

    private final BeerDto beerDto;
}
