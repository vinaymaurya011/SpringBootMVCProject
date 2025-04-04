package com.techboot.springbootwebtutorial.springbootwebtutorial.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techboot.springbootwebtutorial.springbootwebtutorial.annotations.EmployeeRoleValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;

    @NotBlank(message = "Name of the employee can't be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in range : [3, 10]")
    private String name;

    @NotBlank(message = "Email of employee can't be blank")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "Age of employee can't be null")
    @Max(value = 80, message = "Age of employee can't be greater than 80")
    @Min(value = 18, message = "Age of employee can't be less than 18")
    private Integer age;

    @NotBlank(message = "Role of employee can't be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "Role of employee can be only ADMIN or USER")
    @EmployeeRoleValidation
    private String role;

    @NotNull(message = "Salary of employee should not be null")
    @Positive(message = "Salary of employee should be positive")
    @Digits(integer = 6, fraction = 2, message = "Salary should be in form of XXXXXX.XX")
    @DecimalMax(value = "100000.99")
    @DecimalMin(value = "100.50")
    private Double salary;

    @PastOrPresent(message = "Date of joining filed in employee can't be in the future")
    private LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be true")
    @JsonProperty("isActive")
    private Boolean isActive;
}
