package ru.testovich.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.entities.AnswerEntity;
import ru.testovich.entities.QuestionEntity;
import ru.testovich.entities.TestEntity;
import ru.testovich.mappers.QuestionMapper;
import ru.testovich.repositories.AnswerRepository;
import ru.testovich.repositories.QuestionRepository;
import ru.testovich.repositories.TestRepository;

@Service
@AllArgsConstructor
public class QuestionService implements IQuestionService {
    private QuestionMapper questionMapper;
    
    private QuestionRepository questionRepository;

    private AnswerRepository answerRepository;

    private TestRepository testRepository;

    @Override
    public ResponseDTO<GetQuestionDTO> createQuestion(CreateQuestionDTO dto) throws ResponseStatusException {
        var response = new ResponseDTO<GetQuestionDTO>();

        try {
            Optional<TestEntity> optTestEntity = this.testRepository.findById(dto.getTestId());

            TestEntity testEntity = optTestEntity.orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, 
                    "Такого теста не существует!"
                )
            );

            QuestionEntity questionEntity = this.questionMapper.toEntity(dto);

            questionEntity.setTest(testEntity);

            this.questionRepository.save(questionEntity);

            for (AnswerEntity answerEntity : questionEntity.getAnswers()) {
                answerEntity.setQuestion(questionEntity);

                this.answerRepository.save(answerEntity);
            }

            GetQuestionDTO questionDTO = this.questionMapper.toGetQuestionDTO(questionEntity);

            response.setData(questionDTO);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Ошибка при работе с базой данных!"
            );
        }

        return response;
    }
}
