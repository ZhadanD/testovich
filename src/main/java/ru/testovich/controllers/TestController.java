package ru.testovich.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ru.testovich.api.TestAPI;
import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetFullTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateTestDTO;
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

    @Override
    public ResponseEntity<ResponseDTO<List<GetTestDTO>>> getTestsCurrentUser() {
        ResponseDTO<List<GetTestDTO>> response = this.testService.getTestsCurrentUser();

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ResponseDTO<GetFullTestDTO>> getTestCurrentUser(Long testId) {
        ResponseDTO<GetFullTestDTO> response = this.testService.getTestCurrentUser(testId);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<ResponseDTO<GetTestDTO>> updateTest(@Valid UpdateTestDTO dto) {
        ResponseDTO<GetTestDTO> response = this.testService.updateTest(dto);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<Void> deleteTest(Long testId) {
        this.testService.deleteTest(testId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
