package ru.testovich.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Schema(description = "DTO для получения ответа")
@Data
@AllArgsConstructor
public class GetAnswerDTO {
    @Schema(description = "id ответа", example = "1")
    private Long id;

    @Schema(description = "Название ответа", example = "Ответ")
    private String name;

    @Schema(description = "Правильный ответ или нет", example = "false")
    private Boolean isTrue;
}
