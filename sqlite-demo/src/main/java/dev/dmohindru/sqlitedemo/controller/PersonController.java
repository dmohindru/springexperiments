package dev.dmohindru.sqlitedemo.controller;

import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/person")
@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    ResponseEntity<List<PersonDTO>> getAllPerson() {
        return new ResponseEntity<>(List.of(
                PersonDTO.builder().name("Dhruv").message("Hello").build(),
                PersonDTO.builder().name("Poonam").message("Hello Poonam").build()),
                HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{personId}")
    ResponseEntity<PersonDTO> updatePerson(@PathVariable("personId") Integer personId, @RequestBody PersonDTO personDTO) {
        personDTO.setName(personDTO.getName() + personId);
        return new ResponseEntity<>(personDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    ResponseEntity<PersonDTO> deletePerson(@PathVariable("personId") Integer personId) {
        PersonDTO deletedPerson = PersonDTO
                .builder()
                .name("Deleted Person " + personId)
                .message("Deleted message")
                .build();

        return new ResponseEntity<>(deletedPerson, HttpStatus.OK);
    }
}
