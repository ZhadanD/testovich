package ru.testovich.services;

import org.springframework.web.server.ResponseStatusException;

import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;

public interface ITestService {
    ResponseDTO<GetTestDTO> createTest(CreateTestDTO dto) throws ResponseStatusException;
}
