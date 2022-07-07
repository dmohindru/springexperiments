package dev.dmohindru.sqlitedemo.service;

import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.entity.Person;

import java.util.List;

public interface PersonService {
    List<Person> getAll();
    Person findById();
    void deleteById(Integer id);
    Person add(PersonDTO personDTO);
    Person update(PersonDTO personDTo);
}
