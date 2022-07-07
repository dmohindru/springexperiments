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
        return new ResponseEntity<>(personService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{personId}")
    ResponseEntity<PersonDTO> getPersonById(@PathVariable("personId") Integer id) {
        return new ResponseEntity<>(personService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<PersonDTO> savePerson(@RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.add(personDTO), HttpStatus.CREATED);
    }

    @PatchMapping("/{personId}")
    ResponseEntity<PersonDTO> updatePerson(@PathVariable("personId") Integer personId, @RequestBody PersonDTO personDTO) {
        return new ResponseEntity<>(personService.update(personId, personDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deletePerson(@PathVariable("personId") Integer personId) {
        personService.deleteById(personId);
    }
}
