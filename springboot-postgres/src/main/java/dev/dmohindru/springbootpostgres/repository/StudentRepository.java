package dev.dmohindru.springbootpostgres.repository;

import dev.dmohindru.springbootpostgres.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Student findFirstByEmail(String email);

    Student findFirstByFirstName(String firstName);

    Student findFirstByLastName(String lastName);
}
