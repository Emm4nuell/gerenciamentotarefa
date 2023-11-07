package br.com.gerenciamentotarefa.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorException {

    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}