package dev.dmohindru.demostudentapi.dto;

import dev.dmohindru.demostudentapi.validation.CourseValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentDTO {

    @Null(message = "id must be null")
    private Long id;

    @NotEmpty(message = "First name cannot be empty")
    @Size(min = 2, message = "First Name should be at least 2 characters long")
    private String firstName;

    @NotEmpty(message = "Last name cannot be empty")
    @Size(min = 2, message = "Last Name should be at least 2 characters long")
    private String lastName;

    @NotEmpty(message = "Email cannot be null")
    @Email(message = "Proper email is required")
    private String email;

    @CourseValidation(message = "Valid Course ID should be provided")
    @NotEmpty(message = "Course Id cannot be null")
    private String courseId;

}
