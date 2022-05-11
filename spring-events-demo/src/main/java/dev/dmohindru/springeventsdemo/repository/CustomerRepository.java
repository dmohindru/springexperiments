package dev.dmohindru.springeventsdemo.repository;

import dev.dmohindru.springeventsdemo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}
