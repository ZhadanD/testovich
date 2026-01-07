package ru.testovich.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO для редактирования ответа")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAnswerDTO {
    @Schema(description = "Название ответа", example = "Ответ")
    @NotNull(message = "Не должно быть пустым!")
    @Size(min = 1, max = 50, message = "Не должно быть меньше 1 и больше 50 символов!")
    private String name;

    @Schema(description = "Правильный ответ или нет", example = "false")
    @NotNull(message = "Не должно быть пустым!")
    private Boolean isTrue;
}
