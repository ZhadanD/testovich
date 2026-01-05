package ru.testovich.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO для создания вопроса")
@Data
@AllArgsConstructor
public class CreateQuestionDTO {
    @Schema(description = "id теста, для которого создается вопрос")
    @NotNull(message = "Не должно быть пустым!")
    private Long testId;

    @Schema(description = "Название вопроса", example = "Вопрос")
    @NotNull(message = "Не должно быть пустым!")
    @Size(min = 1, max = 50, message = "Не должно быть меньше 1 и больше 50 символов!")
    private String name;

    @Schema(description = "Ответы на вопрос")
    @Valid
    private List<CreateAnswerDTO> answers;
}
