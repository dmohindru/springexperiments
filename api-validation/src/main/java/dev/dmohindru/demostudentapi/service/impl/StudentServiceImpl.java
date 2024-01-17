package dev.dmohindru.demostudentapi.service.impl;

import dev.dmohindru.demostudentapi.dto.StudentDTO;
import dev.dmohindru.demostudentapi.entity.Student;
import dev.dmohindru.demostudentapi.exception.StudentException;
import dev.dmohindru.demostudentapi.repository.StudentRepository;
import dev.dmohindru.demostudentapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public StudentDTO saveNewStudent(StudentDTO studentDTO) {
        Student student = Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .courseId(studentDTO.getCourseId())
                .build();

        Student savedStudent = studentRepository.save(student);

        return getStudentDTO(savedStudent);
    }

    @Override
    public StudentDTO getStudentById(Long id) {

        Student foundStudent = studentRepository
                .findById(id)
                .orElseThrow(() -> new StudentException(
                        String.format("Student by id [%s] not found", id)
                ));
        return getStudentDTO(foundStudent);
    }

    private StudentDTO getStudentDTO(Student student) {
        return StudentDTO
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .courseId(student.getCourseId())
                .build();

    }
}
