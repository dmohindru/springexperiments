package dev.dmohindru.springbootpostgres.service;

import dev.dmohindru.springbootpostgres.dto.StudentDto;
import dev.dmohindru.springbootpostgres.entity.Student;

public interface StudentService {
    Student findStudentByEmail(String email);
    Student findStudentByFirstname(String firstName);
    Student findStudentByLastname(String lastName);
    Student saveStudent(StudentDto studentDto);
}
