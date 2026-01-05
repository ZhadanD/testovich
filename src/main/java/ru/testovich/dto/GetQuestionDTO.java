package ru.testovich.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO для получения вопроса")
@Data
@AllArgsConstructor
public class GetQuestionDTO {
    @Schema(description = "id вопроса", example = "1")
    private Long id;

    @Schema(description = "Название вопроса", example = "Вопрос")
    private String name;

    @Schema(description = "Ответы на вопрос")
    private List<GetAnswerDTO> answers;
}
