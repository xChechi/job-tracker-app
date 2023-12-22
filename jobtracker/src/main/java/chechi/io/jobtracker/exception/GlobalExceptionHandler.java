package chechi.io.jobtracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JobApplicationNotFound.class)
    public ResponseEntity<String> handlerJobApplicationNotFound (JobApplicationNotFound message) {
        return new ResponseEntity<>(message.getMessage(), HttpStatus.NOT_FOUND);
    }
}
