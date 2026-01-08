package ru.testovich.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.testovich.enums.TypeTestEnum;

@Schema(description = "DTO для редактирования теста")
@Data
@AllArgsConstructor
public class UpdateTestDTO {
    @Schema(description = "id теста", example = "1")
    @NotNull(message = "Не должно быть пустым.")
    private Long id;

    @Schema(description = "Название теста", example = "Тест")
    @NotNull(message = "Не должно быть пустым.")
    @Size(min = 1, max = 50, message = "Не должно быть меньше 1 и больше 50 символов!")
    private String name;

    @Schema(description = "Тип теста")
    @NotNull(message = "Не должно быть пустым.")
    private TypeTestEnum type;

    @Schema(description = "Является ли тест публичным")
    @NotNull(message = "Не должно быть пустым.")
    private Boolean isPublic;
}
