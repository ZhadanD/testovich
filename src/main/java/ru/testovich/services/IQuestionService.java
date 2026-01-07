package ru.testovich.services;

import org.springframework.web.server.ResponseStatusException;

import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateQuestionDTO;

public interface IQuestionService {
    ResponseDTO<GetQuestionDTO> createQuestion(CreateQuestionDTO dto) throws ResponseStatusException;

    ResponseDTO<GetQuestionDTO> updateQuestion(UpdateQuestionDTO dto) throws ResponseStatusException;
}
