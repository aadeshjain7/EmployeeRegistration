package in.learn.employeeregistration.advice;

import in.learn.employeeregistration.customexceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class EmployeeGlobalExceptionHandler {

    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> exception1(ResourceNotFoundException e) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.
                builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .msg(e.getMessage()).build();
        return new ResponseEntity<>(buildApiResponse(apiErrorResponse),apiErrorResponse.getStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<?>> exception2(Exception e) {
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.
                builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .msg(e.getMessage()).build();
        return new ResponseEntity<>(buildApiResponse(apiErrorResponse),apiErrorResponse.getStatus());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> exception3(MethodArgumentNotValidException e) {
        List<String> errors=e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(error->error.getDefaultMessage())
                .collect(Collectors.toList());
        ApiErrorResponse apiErrorResponse = ApiErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .msg("Inputs are not valid")
                .subErrors(errors)
                .build();
        return new ResponseEntity<>(buildApiResponse(apiErrorResponse),apiErrorResponse.getStatus());
    }
    public ApiResponse<?> buildApiResponse(ApiErrorResponse apiErrorResponse){
        return new ApiResponse<>(apiErrorResponse);
    }
}
