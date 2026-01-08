package ru.testovich.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateQuestionDTO;

@Tag(name = "API вопросов")
@RequestMapping("/api/v1/questions")
public interface QuestionAPI {
    @Operation(summary = "Создание вопроса с ответами на него")
    @PostMapping
    ResponseEntity<ResponseDTO<GetQuestionDTO>> createQuestion(@Valid @RequestBody CreateQuestionDTO dto);

    @Operation(summary = "Редактирование вопроса с его ответами")
    @PutMapping
    ResponseEntity<ResponseDTO<GetQuestionDTO>> updateQuestion(@Valid @RequestBody UpdateQuestionDTO dto);

    @Operation(summary = "Удаление вопроса с его ответами")
    @DeleteMapping("/{questionId}")
    ResponseEntity<Void> deleteQuestion(@PathVariable("questionId") Long questionId);
}
