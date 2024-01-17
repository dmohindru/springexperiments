package dev.dmohindru.demostudentapi.repository;

import dev.dmohindru.demostudentapi.entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

}
