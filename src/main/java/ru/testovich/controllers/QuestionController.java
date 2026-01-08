package ru.testovich.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ru.testovich.api.QuestionAPI;
import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateQuestionDTO;
import ru.testovich.services.IQuestionService;

@RestController
@AllArgsConstructor
public class QuestionController implements QuestionAPI {
    private IQuestionService questionService;

    @Override
    public ResponseEntity<ResponseDTO<GetQuestionDTO>> createQuestion(@Valid CreateQuestionDTO dto) {
        ResponseDTO<GetQuestionDTO> response = this.questionService.createQuestion(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                             .body(response);
    }

    @Override
    public ResponseEntity<ResponseDTO<GetQuestionDTO>> updateQuestion(@Valid UpdateQuestionDTO dto) {
        ResponseDTO<GetQuestionDTO> response = this.questionService.updateQuestion(dto);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteQuestion(Long questionId) {
        this.questionService.deleteQuestion(questionId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
