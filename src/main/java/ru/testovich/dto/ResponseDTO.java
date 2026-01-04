package ru.testovich.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO для получения ответа")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    @Schema(description = "Информация с сервера")
    private T data;

    @Schema(description = "Список ошибок")
    private List<ErrorDTO> errors = new ArrayList<>();
}
