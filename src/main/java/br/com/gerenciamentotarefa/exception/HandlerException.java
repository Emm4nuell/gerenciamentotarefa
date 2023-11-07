package br.com.gerenciamentotarefa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorException> nullPointerException(NullPointerException exception, HttpServletRequest request){
        ErrorException err = new ErrorException(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                exception.getMessage().toString(),
                request.getPathInfo().toString()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }
}
