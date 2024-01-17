package dev.dmohindru.demostudentapi.controller;

import dev.dmohindru.demostudentapi.dto.StudentDTO;
import dev.dmohindru.demostudentapi.exception.StudentException;
import dev.dmohindru.demostudentapi.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentDTO,
                                                    BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            String errors = bindingResult
                    .getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .reduce((str1, str2) -> str1 + "," + str2)
                    .orElse("Error");

            throw new StudentException(errors);
        }

        StudentDTO savedStudentDTO = studentService.saveNewStudent(studentDTO);


        return new ResponseEntity<>(savedStudentDTO, HttpStatus.CREATED);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<StudentDTO> getStudentById(
            @PathVariable("studentId") Long id,
            @RequestParam(name="lastname", required = false) String lastname
    ) {

        StudentDTO foundStudentDTO = studentService.getStudentById(id);
        return new ResponseEntity<>(foundStudentDTO, HttpStatus.FOUND);
    }


}
