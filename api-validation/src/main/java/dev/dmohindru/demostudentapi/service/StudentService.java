package dev.dmohindru.demostudentapi.service;

import dev.dmohindru.demostudentapi.dto.StudentDTO;

public interface StudentService {
    StudentDTO saveNewStudent(StudentDTO studentDTO);

    StudentDTO getStudentById(Long id);
}
