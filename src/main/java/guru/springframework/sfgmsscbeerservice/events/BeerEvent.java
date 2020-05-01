/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.events;

import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * beer event
 *
 * @author Martin Wunderlich
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BeerEvent {

    static final long serialVersionUID = 5333452867634730824L;

    private BeerDto beerDto;
}
