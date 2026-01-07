package ru.testovich.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "DTO для редактирования вопроса")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuestionDTO {
    @Schema(description = "id вопроса")
    @NotNull(message = "Не должно быть пустым!")
    private Long id;

    @Schema(description = "Название вопроса", example = "Вопрос")
    @NotNull(message = "Не должно быть пустым!")
    @Size(min = 1, max = 50, message = "Не должно быть меньше 1 и больше 50 символов!")
    private String name;

    @Schema(description = "Ответы на вопрос")
    @Valid
    private List<UpdateAnswerDTO> answers;
}
