package guru.springframework.sfgmsscbeerservice.bootstrap;

import guru.springframework.sfgmsscbeerservice.domain.Beer;
import guru.springframework.sfgmsscbeerservice.repositories.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    public static final String BEER_UPC_1 = "1234578";
    public static final String BEER_UPC_2 = "45767899";
    public static final String BEER_UPC_3 = "9877655642";

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadBeerObjects();
    }

    private void loadBeerObjects() {
        if (beerRepository.count() == 0) {
            beerRepository.save(Beer.builder().
                    beerName("name 1").
                    beerStyle("style 1").
                    quantityToBrew(200).
                    minOnHand(12).
                    upc(BEER_UPC_1).
                    price(new BigDecimal(12.95)).
                    build());

            beerRepository.save(Beer.builder().
                    beerName("name 2").
                    beerStyle("style 2").
                    quantityToBrew(200).
                    minOnHand(12).
                    upc(BEER_UPC_2).
                    price(new BigDecimal(11.95)).
                    build());

            beerRepository.save(Beer.builder().
                    beerName("name 3").
                    beerStyle("style 3").
                    quantityToBrew(20).
                    minOnHand(12).
                    upc(BEER_UPC_3).
                    price(new BigDecimal(11.95)).
                    build());
        }
    }
}
