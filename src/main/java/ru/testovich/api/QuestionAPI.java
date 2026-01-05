package ru.testovich.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.dto.ResponseDTO;

@Tag(name = "API вопросов")
@RequestMapping("/api/v1/questions")
public interface QuestionAPI {
    @Operation(summary = "Создание вопроса с ответами на него")
    @PostMapping
    ResponseEntity<ResponseDTO<GetQuestionDTO>> createQuestion(@Valid @RequestBody CreateQuestionDTO dto);
}
