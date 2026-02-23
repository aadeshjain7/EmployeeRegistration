package in.learn.employeeregistration.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ApiErrorResponse {
    private String msg;
    private HttpStatus status;
    private List<String> subErrors;
}
