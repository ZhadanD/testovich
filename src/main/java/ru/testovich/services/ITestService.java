package ru.testovich.services;

import java.util.List;

import org.springframework.web.server.ResponseStatusException;

import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetFullTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateTestDTO;

public interface ITestService {
    ResponseDTO<GetTestDTO> createTest(CreateTestDTO dto) throws ResponseStatusException;

    ResponseDTO<List<GetTestDTO>> getTestsCurrentUser() throws ResponseStatusException;

    ResponseDTO<GetFullTestDTO> getTestCurrentUser(Long testId) throws ResponseStatusException;

    ResponseDTO<GetTestDTO> updateTest(UpdateTestDTO dto) throws ResponseStatusException;
}
