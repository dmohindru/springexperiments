package dev.dmohindru.sqlitedemo.exceptions;

import dev.dmohindru.sqlitedemo.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class APIControllerAdvice {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorDTO> handleError(RuntimeException runtimeException, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorDTO
                .builder()
                .error(HttpStatus.NOT_FOUND.value())
                .message(runtimeException.getMessage())
                .build(), HttpStatus.NOT_FOUND);

    }
}
