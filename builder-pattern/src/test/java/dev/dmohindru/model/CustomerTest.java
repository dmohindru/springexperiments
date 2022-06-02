package dev.dmohindru.model;

import org.junit.jupiter.api.Test;

public class CustomerTest {

    @Test
    void testCustomer() {
        Customer customer = Customer
                .builder()
                .firstName("Dhruv")
                .lastName("Mohindru")
                .age(40)
                .phone("0411910745")
                .build();

        System.out.println(customer);

    }
}
