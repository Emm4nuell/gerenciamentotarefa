package br.com.gerenciamentotarefa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorException> nullPointerException(NullPointerException exception, HttpServletRequest request){

        ErrorException err = new ErrorException(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Internal Server Error...",
                exception.getMessage().toString(),
                request.getRequestURI().toString(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorException> methodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request){
        List<FieldError> errorfiled = exception.getBindingResult().getFieldErrors();

        List<ValidException> valid = errorfiled.stream()
                .map(x -> new ValidException(x.getField(), x.getDefaultMessage())).collect(Collectors.toList());

        ErrorException error = new ErrorException(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "Campo obrigatório!",
                "Campos deve ser válido!",
                request.getRequestURI().toString(),
                valid);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
