package dev.dmohindru.sqlitedemo.service;

import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.entity.Person;

import java.util.List;

public interface PersonService {
    List<PersonDTO> getAll();
    PersonDTO findById(Integer id);
    void deleteById(Integer id);
    PersonDTO add(PersonDTO personDTO);
    PersonDTO update(Integer id, PersonDTO personDTo);
}
