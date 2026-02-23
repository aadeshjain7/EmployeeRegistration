package in.learn.employeeregistration.advice;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T>{
//    @JsonProperty(value = "Time")
//    @JsonFormat(timezone = "hh:mm:ss dd-MM-yyyy")
    private LocalDateTime localDateTime;
    private T data;
    @JsonProperty(value = "error")
    private ApiErrorResponse apiErrorResponse;


    public ApiResponse() {
        this.localDateTime = LocalDateTime.now();
    }

    public ApiResponse(T data) {
        this();
        this.data = data;
    }

    public ApiResponse(ApiErrorResponse apiErrorResponse) {
        this();
        this.apiErrorResponse = apiErrorResponse;
    }
}
