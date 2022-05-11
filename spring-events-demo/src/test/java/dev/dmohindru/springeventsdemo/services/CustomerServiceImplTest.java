package dev.dmohindru.springeventsdemo.services;


import dev.dmohindru.springeventsdemo.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;


@SpringBootTest
class CustomerServiceImplTest {

    @Autowired
    private CustomerService customerService;

    @Test
    public void testCustomerSave() {
        Customer customer = Customer.builder().id(UUID.randomUUID()).firstName("David").lastName("Jones").build();
        // add customer
        customerService.addCustomer(customer);
        // delete customer
        //customerService.deleteCustomer(customer.getId());
    }

}