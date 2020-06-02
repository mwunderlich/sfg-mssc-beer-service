package guru.springframework.sfgmsscbeerservice.web.mappers;

import guru.sfg.brewery.model.CustomerDto;
import guru.springframework.sfgmsscbeerservice.domain.Customer;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDto CustomerToCustomerDto(Customer customer);
    Customer CustomerDtoToCustomer(CustomerDto customerDto);
}
