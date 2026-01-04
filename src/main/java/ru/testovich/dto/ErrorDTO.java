package ru.testovich.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO для описания ошибки")
@Data
@AllArgsConstructor
public class ErrorDTO {
    private String name;

    private String message;
}
