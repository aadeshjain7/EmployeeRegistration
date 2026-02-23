package in.learn.employeeregistration.service;

import in.learn.employeeregistration.customexceptions.ResourceNotFoundException;
import in.learn.employeeregistration.config.mapper.EmployeeMapper;
import in.learn.employeeregistration.dto.EmployeeDto;
import in.learn.employeeregistration.entity.EmployeeEntity;
import in.learn.employeeregistration.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public List<EmployeeDto> getAll() {
        List<EmployeeEntity> employeeEntities=employeeRepository.findAll();
//        employeeMapper.employeeDtoList(employeeEntities);
       return employeeEntities.stream()
               .map(e->new EmployeeDto(e.getId(), e.getName(),e.getAge(),e.getEmail(),e.getSalary(),e.getDoj(),e.getRole(),e.isActive()))
               .collect(Collectors.toList());

    }

    public EmployeeDto createNewEmployee(EmployeeDto newEmployee) {
        EmployeeEntity employeeEntity=employeeMapper.
                toEmployeeEntity(newEmployee);
        EmployeeEntity savedEntity=employeeRepository.save(employeeEntity);
        System.out.println(savedEntity.getId());
        return employeeMapper.toEmployeeDto(savedEntity);

    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto updateDtoInfo, Long id) {
        EmployeeEntity employeeEntity=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User not Found "+id) );
        BeanUtils.copyProperties(updateDtoInfo,employeeEntity,"id");
        employeeEntity.setId(id);
        employeeRepository.save(employeeEntity);
        return employeeMapper.toEmployeeDto(employeeEntity);
    }

    public boolean isExistsById(Long id){
        return employeeRepository.existsById(id);
    }
    @Override
    public String deleteById(Long id) {
       if(isExistsById(id)){
           employeeRepository.deleteById(id);
           return "Id deleted "+id;
       }else{
           throw new ResourceNotFoundException("Id not found "+id);
       }
    }

    @Override
    public EmployeeDto updateField(Map<String, Object> updates, Long id) {
        if (isExistsById(id)) {
            EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
            updates.forEach((key, value) -> {
                Field fieldtobeupdated = ReflectionUtils.findField(EmployeeEntity.class, key);
                fieldtobeupdated.setAccessible(true);
                ReflectionUtils.setField(fieldtobeupdated, employeeEntity, value);
            });
            return employeeMapper.toEmployeeDto(employeeRepository.save(employeeEntity));
        }else throw new ResourceNotFoundException("Id not Found "+id);
    }

    public EmployeeDto getById(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.
                findById(id).orElseThrow(() -> new ResourceNotFoundException("User Not Found "+id));
        return employeeMapper.toEmployeeDto(employeeEntity);
    }
}
