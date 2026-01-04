package ru.testovich.advices;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import ru.testovich.dto.ErrorDTO;
import ru.testovich.dto.ResponseDTO;

@ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDTO<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<ErrorDTO> errors = e.getFieldErrors()
                                    .stream()
                                    .map(error -> new ErrorDTO(
                                                    error.getField(), 
                                                    error.getDefaultMessage()
                                                    )
                                    )
                                    .toList();

        var response = new ResponseDTO<Void>();

        response.setErrors(errors);

        return ResponseEntity
                .status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ResponseDTO<Void>> handleResponseStatusException(ResponseStatusException e) {
        var response = new ResponseDTO<Void>();

        response.setErrors(
            List.of(
                new ErrorDTO(
                    "Ошибка",
                    e.getReason()
                )
            )
        );

        return ResponseEntity
                .status(e.getStatusCode())
                .body(response);
    }
}
