package in.learn.employeeregistration.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmployeeTable")
public class EmployeeEntity extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private  Integer age;
    private String email;
    private Double salary;
    private LocalDate doj;
    private String role;
    @JsonProperty("isActive")
    private boolean isActive;

    @PrePersist
    void beforeSave(){

    }
    @PreUpdate
    void beforeUpdate(){
    }
    @PreRemove
    void remove(){

    }
}
