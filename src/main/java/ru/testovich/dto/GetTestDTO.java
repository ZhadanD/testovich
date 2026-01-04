package ru.testovich.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.testovich.enums.TypeTestEnum;

@Schema(description = "DTO для получения теста")
@Data
@AllArgsConstructor
public class GetTestDTO {
    @Schema(description = "id теста", example = "1")
    private Long id;

    @Schema(description = "Название теста", example = "Тест")
    private String name;

    @Schema(description = "Тип теста")
    private TypeTestEnum type;

    @Schema(description = "Является ли тест публичным")
    private Boolean isPublic;
}
