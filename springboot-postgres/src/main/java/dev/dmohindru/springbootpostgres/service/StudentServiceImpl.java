package dev.dmohindru.springbootpostgres.service;

import dev.dmohindru.springbootpostgres.dao.StudentDao;
import dev.dmohindru.springbootpostgres.dto.StudentDto;
import dev.dmohindru.springbootpostgres.entity.Student;
import dev.dmohindru.springbootpostgres.exceptions.NotFoundExceptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentDao studentDao;

    @Override
    public Student findStudentByEmail(String email) {
        Student student = studentDao.findStudentByEmail(email);
        if (student == null)
            throw new NotFoundExceptions(String.format("Student with email [%s] not found", email));
        return student;
    }

    @Override
    public Student findStudentByFirstname(String firstName) {
        Student student = studentDao.findStudentByFirstname(firstName);
        if (student == null)
            throw new NotFoundExceptions(String.format("Student with first name [%s] not found", firstName));
        return student;
    }

    @Override
    public Student findStudentByLastname(String lastName) {
        Student student = studentDao.findStudentByLastname(lastName);
        if (student == null)
            throw new NotFoundExceptions(String.format("Student with last name [%s] not found", lastName));
        return student;
    }

    @Override
    public Student saveStudent(StudentDto studentDto) {
        Student student = Student.builder()
                .firstName(studentDto.getFirstName())
                .lastName(studentDto.getLastName())
                .email(studentDto.getEmail())
                .build();

        return studentDao.saveStudent(student);
    }
}
