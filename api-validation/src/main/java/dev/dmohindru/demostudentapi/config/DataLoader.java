package dev.dmohindru.demostudentapi.config;

import dev.dmohindru.demostudentapi.constants.CourseID;
import dev.dmohindru.demostudentapi.entity.Student;
import dev.dmohindru.demostudentapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataLoader implements CommandLineRunner {
    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
      log.info("Adding student to db");
        List<Student> students = Stream.iterate(0, i -> i + 1)
                .limit(10)
                .map(i -> Student
                        .builder()
                        .firstName("First Name")
                        .lastName("Last Name")
                        .email("someemail@email.com")
                        .courseId(CourseID.ENG.toString())
                        .build())
                .toList();

        Student.builder().build();

        studentRepository.saveAll(students);

    }
}
