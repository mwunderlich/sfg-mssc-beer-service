package guru.springframework.sfgmsscbeerservice.web.mappers;

import guru.springframework.sfgmsscbeerservice.domain.Customer;
import guru.springframework.sfgmsscbeerservice.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDto CustomerToCustomerDto(Customer customer);
    Customer CustomerDtoToCustomer(CustomerDto customerDto);
}
