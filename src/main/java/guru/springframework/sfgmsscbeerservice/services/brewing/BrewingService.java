/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.services.brewing;

import guru.springframework.sfgmsscbeerservice.domain.Beer;
import guru.springframework.sfgmsscbeerservice.events.BrewBeerEvent;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import guru.springframework.sfgmsscbeerservice.services.inventory.BeerInventoryService;
import guru.springframework.sfgmsscbeerservice.web.mappers.BeerMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

import static guru.springframework.sfgmsscbeerservice.config.JmsConfig.BREWING_REQUEST_QUEUE;

/**
 * Brewing service
 *
 * @author Martin Wunderlich
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BrewingService {
    private final BeerRepository beerRepository;
    private final BeerInventoryService beerInventoryService;
    private final JmsTemplate jmsTemplate;
    private final BeerMapper beerMapper;

    @Scheduled(fixedRate = 5000)
    public void checkForLowInventory() {
        List<Beer> beers = beerRepository.findAll();

        beers.forEach(beer -> {
            Integer invQoH = beerInventoryService.getOnhandInventory(beer.getId().toString());

            log.debug("Min on-hand required: " + beer.getMinOnHand());
            log.debug("On hand: " + invQoH);

            if (beer.getMinOnHand() > invQoH) {
                jmsTemplate.convertAndSend(BREWING_REQUEST_QUEUE, new BrewBeerEvent(beerMapper.beerToBeerDto(beer)));
            }
        });
    }
}
