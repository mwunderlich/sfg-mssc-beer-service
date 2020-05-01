/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.services.brewing;

import guru.springframework.sfgmsscbeerservice.config.JmsConfig;
import guru.springframework.sfgmsscbeerservice.domain.Beer;
import guru.springframework.sfgmsscbeerservice.events.BrewBeerEvent;
import guru.springframework.sfgmsscbeerservice.events.NewInventoryEvent;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import guru.springframework.sfgmsscbeerservice.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * JMS Listener
 *
 * @author Martin Wunderlich
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BrewBeerListener {

    private final BeerRepository beerRepository;
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.BREWING_REQUEST_QUEUE)
    public void listen(BrewBeerEvent event) {
        BeerDto beerDto = event.getBeerDto();

        Beer beer = beerRepository.getOne(beerDto.getId());

        beerDto.setQuantityOnHand(beer.getQuantityToBrew());

        NewInventoryEvent newInventoryEvent = new NewInventoryEvent(beerDto);

        log.debug("Brewed beer: " + beer.getMinOnHand());

        jmsTemplate.convertAndSend(JmsConfig.NEW_INVENTORY_QUEUE, newInventoryEvent);
    }
}
