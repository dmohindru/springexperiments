package dev.dmohindru.springbootpostgres.dao;

import dev.dmohindru.springbootpostgres.entity.Student;

public interface StudentDao {
    Student findStudentByEmail(String email);
    Student findStudentByFirstname(String firstName);
    Student findStudentByLastname(String lastName);
    Student saveStudent(Student student);
}
