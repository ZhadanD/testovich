package ru.testovich.services;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;

public interface ITestService {
    ResponseDTO<GetTestDTO> createTest(CreateTestDTO dto) throws ResponseStatusException;

    ResponseDTO<List<GetTestDTO>> getTestsCurrentUser() throws ResponseStatusException;
}
