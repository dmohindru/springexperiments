package dev.dmohindru.sqlitedemo.dao;

import dev.dmohindru.sqlitedemo.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}
