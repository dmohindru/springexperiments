package dev.dmohindru.springeventsdemo.services;

import dev.dmohindru.springeventsdemo.domain.Customer;
import dev.dmohindru.springeventsdemo.events.CustomerAddEvent;
import dev.dmohindru.springeventsdemo.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService{

    private final CustomerRepository customerRepository;
    private final ApplicationEventPublisher publisher;

    @Override
    public Customer addCustomer(Customer customer) {
        publisher.publishEvent(new CustomerAddEvent(customer, "Customer created"));
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(UUID customerId) {
        Customer customer = Customer.builder().id(customerId).firstName("John").build();
        publisher.publishEvent(new CustomerAddEvent(customer, "Customer deleted"));
        customerRepository.deleteById(customerId);
    }
}
