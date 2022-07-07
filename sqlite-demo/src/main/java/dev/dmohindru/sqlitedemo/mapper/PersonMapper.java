package dev.dmohindru.sqlitedemo.mapper;

import dev.dmohindru.sqlitedemo.dto.PersonDTO;
import dev.dmohindru.sqlitedemo.entity.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PersonMapper {

    @Mapping(target = "id", ignore = true)
    Person personDTOtoPerson(PersonDTO personDTO);

    PersonDTO personToPersonDTO(Person person);
}
