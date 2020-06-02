package guru.springframework.sfgmsscbeerservice.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.sfg.brewery.model.CustomerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void getCustomerById() throws Exception {
        mockmvc.perform(get("/api/v1/Customer/" + UUID.randomUUID().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewCustomer() throws Exception {
        CustomerDto customer = CustomerDto.builder().customerName("test 1").build();
        mockmvc.perform(post("/api/vi/Customer/", customer)).andExpect(status().isOk());
    }

    @Test
    void updateCustomer() {
    }
}