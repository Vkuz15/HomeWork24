package pro.sky.JavaSkyPro.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeFullException extends RuntimeException{
    public EmployeeFullException(String message) {
        super(message);
    }
}
