package ru.testovich.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.UpdateTestDTO;
import ru.testovich.enums.TypeTestEnum;

@SpringBootTest
@AutoConfigureMockMvc
public class TestControllerIT {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateTest_forbidden() throws Exception {
        var newTest = new CreateTestDTO(
            "Тест",
            TypeTestEnum.FINAL_CHECK,
            false
        );

        mockMvc.perform(
            post("/api/v1/tests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(newTest))
        )
        .andExpect(status().isForbidden());
    }

    @Test
    void testGetTestsCurrentUser_forbidden() throws Exception {
        mockMvc.perform(
            get("/api/v1/tests/my")
        )
        .andExpect(status().isForbidden());
    }

    @Test
    void testGetTestCurrentUser_forbidden() throws Exception {
        mockMvc.perform(
            get("/api/v1/tests/my/1")
        )
        .andExpect(status().isForbidden());
    }

    @Test
    void testUpdateTest_forbidden() throws Exception {
        var test = new UpdateTestDTO(
            1L,
            "Тест",
            TypeTestEnum.FINAL_CHECK,
            false
        );

        mockMvc.perform(
            put("/api/v1/tests")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(test))
        )
        .andExpect(status().isForbidden());
    }

    @Test
    void testDeleteTest_forbidden() throws Exception {
        mockMvc.perform(
            delete("/api/v1/tests/1")
        )
        .andExpect(status().isForbidden());
    }
}
