package dev.dmohindru.springeventsdemo.services;

import dev.dmohindru.springeventsdemo.domain.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer addCustomer(Customer customer);
    void deleteCustomer(UUID customerId);
}
