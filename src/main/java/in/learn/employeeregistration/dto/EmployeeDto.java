package in.learn.employeeregistration.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import in.learn.employeeregistration.annotations.EmployeeCustomValidations;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Long id;
    @NotBlank(message = "Name can not be blank") @Size(min = 3,max=20,message = "Name should be between 3 and 20 characters")
    private String name;
    @NotNull(message = "Age should not be null") @Min(value = 18,message = "Age should be between 18 and 80") @Max(value = 80,message = "Age should be between 18 and 80")
    private Integer age;
    @NotBlank(message = "Email is mandatory") @Email(message = "Not in email format")
    private String email;
    @NotNull(message = "Salary cannot be null")@Positive(message = "Salary should be positive") @Digits(fraction = 2,integer = 5,message = "Salary should be in form XXXXX.XX") @DecimalMax(value = "100000.99") @DecimalMin(value = "1000.01")
    private Double salary;
    @NotNull(message = "Should no be Null") @PastOrPresent(message = "Future dates are not allowed here")
    private LocalDate doj;
//    @Pattern(regexp = "^(ADMIN|USER)$" , message = "Role can be either Admin or User")
    @EmployeeCustomValidations
    private String role;
    @JsonProperty("isActive")
    @AssertTrue(message = "Employee should be active")
    private boolean isActive;
}

