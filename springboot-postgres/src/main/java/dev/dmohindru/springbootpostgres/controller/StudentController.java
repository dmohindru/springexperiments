package dev.dmohindru.springbootpostgres.controller;

import dev.dmohindru.springbootpostgres.dto.StudentDto;
import dev.dmohindru.springbootpostgres.entity.Student;
import dev.dmohindru.springbootpostgres.exceptions.InvalidRequestParameters;
import dev.dmohindru.springbootpostgres.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    Student getStudent(@RequestParam(name = "email") String email) {

        if (email != null)
            return studentService.findStudentByEmail(email);
//        else if (firstName != null)
//            return studentService.findStudentByFirstname(firstName);
//        else if (lastName != null)
//            return studentService.findStudentByLastname(lastName);

        throw new InvalidRequestParameters("Invalid request parameter");
    }

    @RequestMapping(method = RequestMethod.POST)
    Student saveStudent(@RequestBody StudentDto studentDto) {
        return studentService.saveStudent(studentDto);
    }
}
