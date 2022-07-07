package dev.dmohindru.sqlitedemo.mapper;

import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person personDTOtoPerson(PersonDTO personDTO);

    PersonDTO personToPersonDTO(Person person);
}
