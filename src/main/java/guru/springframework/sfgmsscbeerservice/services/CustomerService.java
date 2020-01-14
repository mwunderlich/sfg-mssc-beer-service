package guru.springframework.sfgmsscbeerservice.services;

import guru.springframework.sfgmsscbeerservice.web.model.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer getCustomerById(UUID customerId);

    Customer saveNewCustomer(Customer customer);

    void updateCustomer(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);
}
