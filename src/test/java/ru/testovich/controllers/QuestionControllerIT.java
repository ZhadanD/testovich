package ru.testovich.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.UpdateQuestionDTO;

@SpringBootTest
@AutoConfigureMockMvc
public class QuestionControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateQuestion_forbidden() throws Exception {
        var createQuestionDTO = new CreateQuestionDTO(
            1L,
            "Вопрос",
            List.of()
        );

        mockMvc.perform(
            post("/api/v1/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createQuestionDTO))
        )
        .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateQuestion_forbidden() throws Exception {
        var updateQuestionDTO = new UpdateQuestionDTO(
            1L,
            "Вопрос",
            List.of()
        );

        mockMvc.perform(
            put("/api/v1/questions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateQuestionDTO))
        )
        .andExpect(status().isForbidden());
    }

    @Test
    void testDeleteQuestion_forbidden() throws Exception {
        mockMvc.perform(
            delete("/api/v1/questions/1")
        )
        .andExpect(status().isForbidden());
    }
}
