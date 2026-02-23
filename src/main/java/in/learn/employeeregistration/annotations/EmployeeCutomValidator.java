package in.learn.employeeregistration.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class EmployeeCutomValidator implements ConstraintValidator<EmployeeCustomValidations,String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null)return false;
        List<String> roles= List.of("USER","ADMIN");
        return roles.contains(value);
    }
}
