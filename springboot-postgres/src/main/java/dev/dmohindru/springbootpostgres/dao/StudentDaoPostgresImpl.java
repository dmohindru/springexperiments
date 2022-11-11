package dev.dmohindru.springbootpostgres.dao;

import dev.dmohindru.springbootpostgres.entity.Student;
import dev.dmohindru.springbootpostgres.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StudentDaoPostgresImpl implements StudentDao {
    private final StudentRepository studentRepository;

    @Override
    public Student findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    @Override
    public Student findStudentByFirstname(String firstName) {
        return studentRepository.findStudentByFirstName(firstName);
    }

    @Override
    public Student findStudentByLastname(String lastName) {
        return studentRepository.findStudentByLastName(lastName);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
}
