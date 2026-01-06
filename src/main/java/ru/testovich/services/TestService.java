package ru.testovich.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetFullTestDTO;
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

    @Override
    public ResponseDTO<List<GetTestDTO>> getTestsCurrentUser() throws ResponseStatusException {
        UserEntity currentUser = this.userService.getCurrentUser();

        var response = new ResponseDTO<List<GetTestDTO>>();

        try {
            List<TestEntity> testEntities = this.testRepository.findTestEntityByUser(currentUser);

            List<GetTestDTO> listTests = this.testMapper.toListGetTestDTO(testEntities);

            response.setData(listTests);
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла ошибка при работе с базой данных"
            );
        }

        return response;
    }

    @Override
    public ResponseDTO<GetFullTestDTO> getTestCurrentUser(Long testId) throws ResponseStatusException {
        UserEntity currentUser = this.userService.getCurrentUser();

        var response = new ResponseDTO<GetFullTestDTO>();

        try {
            Optional<TestEntity> optTestEntity = this.testRepository.findTestEntityByUserAndId(currentUser, testId);

            TestEntity testEntity = optTestEntity.orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Тест не найден"
                )
            );

            GetFullTestDTO fullTestDTO = this.testMapper.toGetFullTestDTO(testEntity);

            response.setData(fullTestDTO);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Произошла ошибка при работе с базой данных"
            );
        }

        return response;
    }
}
