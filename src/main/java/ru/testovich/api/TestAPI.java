package ru.testovich.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;

@Tag(name = "API тестов")
@RequestMapping("/api/v1/tests")
public interface TestAPI {
    @Operation(summary = "Создание теста")
    @PostMapping
    ResponseEntity<ResponseDTO<GetTestDTO>> createTest(@Valid @RequestBody CreateTestDTO dto);
}
