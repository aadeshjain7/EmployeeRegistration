package in.learn.employeeregistration.config.mapper;

import in.learn.employeeregistration.dto.EmployeeDto;
import in.learn.employeeregistration.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.List;


@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto
            (EmployeeEntity employeeEntity);
    EmployeeEntity toEmployeeEntity(EmployeeDto employeeDto);
    List<EmployeeDto> employeeDtoList(List<EmployeeEntity> employeeEntities );
}
