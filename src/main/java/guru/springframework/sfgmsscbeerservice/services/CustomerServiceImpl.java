/*
 * Copyright (c) by censhare AG
 */
package guru.springframework.sfgmsscbeerservice.services;

import guru.sfg.brewery.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service impl
 *
 * @author Martin Wunderlich
 */
@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public CustomerDto getCustomerById(UUID customerId) {
        return CustomerDto.builder().id(customerId)
                .customerName("Thirsty Me")
                .build();
    }

    @Override
    public CustomerDto saveNewCustomer(CustomerDto customerDto) {
        return CustomerDto.builder().id(UUID.randomUUID()).build();
    }

    @Override
    public void updateCustomer(UUID customerId, CustomerDto customerDto) {
        //TODO Impl
    }

    @Override
    public void deleteCustomerById(UUID customerId) {
        log.debug("deleting customer with id " + customerId);
    }
}
