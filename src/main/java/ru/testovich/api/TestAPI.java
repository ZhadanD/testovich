package ru.testovich.api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetFullTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateTestDTO;

@Tag(name = "API тестов")
@RequestMapping("/api/v1/tests")
public interface TestAPI {
    @Operation(summary = "Создание теста")
    @PostMapping
    ResponseEntity<ResponseDTO<GetTestDTO>> createTest(@Valid @RequestBody CreateTestDTO dto);

    @Operation(summary = "Получение тестов авторизованного пользователя")
    @GetMapping("/my")
    ResponseEntity<ResponseDTO<List<GetTestDTO>>> getTestsCurrentUser();

    @Operation(summary = "Получение теста авторизованного пользователя")
    @GetMapping("/my/{testId}")
    ResponseEntity<ResponseDTO<GetFullTestDTO>> getTestCurrentUser(@PathVariable("testId") Long testId);

    @Operation(summary = "Редактирование теста")
    @PutMapping
    ResponseEntity<ResponseDTO<GetTestDTO>> updateTest(@Valid @RequestBody UpdateTestDTO dto);
}
