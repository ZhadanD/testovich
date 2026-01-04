package ru.testovich.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.entities.TestEntity;
import ru.testovich.entities.UserEntity;
import ru.testovich.mappers.TestMapper;
import ru.testovich.repositories.TestRepository;

@Service
@AllArgsConstructor
public class TestService implements ITestService {
    private TestRepository testRepository;

    private TestMapper testMapper;

    private IUserService userService;

    @Override
    public ResponseDTO<GetTestDTO> createTest(CreateTestDTO dto) throws ResponseStatusException {
        TestEntity testEntity = this.testMapper.toEntity(dto);

        try {
            UserEntity userEntity = this.userService.getCurrentUser();

            testEntity.setUser(userEntity);

            this.testRepository.save(testEntity);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла ошибка при работе с базой данных"
            );
        }

        GetTestDTO testDTO = this.testMapper.toGetTestDTO(testEntity);

        var response = new ResponseDTO<GetTestDTO>();

        response.setData(testDTO);

        return response;
    }
    
}
