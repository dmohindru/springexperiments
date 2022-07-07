package dev.dmohindru.sqlitedemo.service;

import dev.dmohindru.sqlitedemo.dao.PersonRepository;
import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.entity.Person;
import dev.dmohindru.sqlitedemo.mapper.PersonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;
    private final PersonMapper personMapper;

    @Override
    public List<PersonDTO> getAll() {
        return personRepository
                .findAll()
                .stream()
                .map(person -> PersonDTO
                        .builder()
                        .id(person.getId())
                        .name(person.getName())
                        .message(person.getMessage())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PersonDTO findById(Integer id) {
        Person foundPerson = personRepository.findById(id).orElse(null);
        if (foundPerson != null) {
            return PersonDTO
                    .builder()
                    .id(foundPerson.getId())
                    .name(foundPerson.getName())
                    .message(foundPerson.getMessage())
                    .build();
        }
        throw new RuntimeException("Not found");
    }

    @Override
    public void deleteById(Integer id) {
        if (!personRepository.existsById(id)) {
            throw new RuntimeException("Not found exception");
        }
        personRepository.deleteById(id);
    }

    @Override
    public PersonDTO add(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setMessage(personDTO.getMessage());
        Person savedPerson = personRepository.save(person);
        log.info("Person saved: {}", savedPerson);
        return PersonDTO.builder()
                .id(savedPerson.getId())
                .name(savedPerson.getName())
                .message(savedPerson.getMessage())
                .build();
    }

    @Override
    public PersonDTO update(Integer id, PersonDTO personDTo) {
        Person person = personRepository.findById(id).orElse(null);
        if (person == null) {
            throw new RuntimeException("Not found exception");
        }

        person.setMessage(personDTo.getMessage());
        person.setName(personDTo.getName());

        Person updatedPerson = personRepository.save(person);
        return PersonDTO
                .builder()
                .id(updatedPerson.getId())
                .name(updatedPerson.getName())
                .message(updatedPerson.getMessage())
                .build();
    }
}
