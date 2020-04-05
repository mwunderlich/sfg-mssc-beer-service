package guru.springframework.sfgmsscbeerservice.services.inventory;

/**
 * Created by jt on 2019-06-07.
 */
public interface BeerInventoryService {

    Integer getOnhandInventory(String beerId);
}
