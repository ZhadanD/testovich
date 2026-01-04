package ru.testovich.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ru.testovich.api.TestAPI;
import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.services.ITestService;

@RestController
@AllArgsConstructor
public class TestController implements TestAPI {
    private ITestService testService;

    @Override
    public ResponseEntity<ResponseDTO<GetTestDTO>> createTest(@Valid CreateTestDTO dto) {
        ResponseDTO<GetTestDTO> response = this.testService.createTest(dto);

        return ResponseEntity.status(HttpStatus.CREATED)
                            .body(response);
    }
}
