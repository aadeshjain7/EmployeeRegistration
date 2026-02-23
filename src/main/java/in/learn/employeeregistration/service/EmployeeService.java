package in.learn.employeeregistration.service;

import in.learn.employeeregistration.dto.EmployeeDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {

    EmployeeDto getById(Long id);

    List<EmployeeDto> getAll();

    EmployeeDto createNewEmployee(EmployeeDto newEmployee);

    EmployeeDto updateEmployee(EmployeeDto updateDtoInfo, Long id);

    String deleteById(Long id);

    EmployeeDto updateField(Map<String, Object> updates, Long id);
}
