package guru.springframework.sfgmsscbeerservice.services.inventory;

import guru.springframework.sfgmsscbeerservice.bootstrap.BeerLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@Disabled // utility for manual testing
@SpringBootTest
class BeerInventoryServiceRestTemplateImplTest {

    @Autowired
    BeerInventoryService beerInventoryService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void getOnhandInventory() {

        Integer qoh = beerInventoryService.getOnhandInventory(BeerLoader.BEER_UPC_1);

        System.out.println(qoh);
    }
}