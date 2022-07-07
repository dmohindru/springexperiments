package dev.dmohindru.sqlitedemo.service;

import dev.dmohindru.sqlitedemo.dao.PersonRepository;
import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.entity.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public List<Person> getAll() {
        return null;
    }

    @Override
    public Person findById() {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Person add(PersonDTO personDTO) {
        return null;
    }

    @Override
    public Person update(PersonDTO personDTo) {
        return null;
    }
}
