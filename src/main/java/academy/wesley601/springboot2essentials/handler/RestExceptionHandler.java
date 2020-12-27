package academy.wesley601.springboot2essentials.handler;

import academy.wesley601.springboot2essentials.exception.BadRequestException;
import academy.wesley601.springboot2essentials.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(
            BadRequestException badRequestException
    ) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .title("Bad Request, check documentation")
                        .details(badRequestException.getMessage())
                        .developerMessage(badRequestException.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
}
