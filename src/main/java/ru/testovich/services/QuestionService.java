package ru.testovich.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetAnswerDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.dto.ResponseDTO;
import ru.testovich.dto.UpdateAnswerDTO;
import ru.testovich.dto.UpdateQuestionDTO;
import ru.testovich.entities.AnswerEntity;
import ru.testovich.entities.QuestionEntity;
import ru.testovich.entities.TestEntity;
import ru.testovich.entities.UserEntity;
import ru.testovich.mappers.AnswerMapper;
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

    private AnswerMapper answerMapper;

    private TestRepository testRepository;

    private IUserService userService;

    @Override
    public ResponseDTO<GetQuestionDTO> createQuestion(CreateQuestionDTO dto) throws ResponseStatusException {
        var response = new ResponseDTO<GetQuestionDTO>();

        try {
            UserEntity userEntity = this.userService.getCurrentUser();
            
            Optional<TestEntity> optTestEntity = this.testRepository.findTestEntityByUserAndId(userEntity, dto.getTestId());

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

    @Override
    @Transactional
    public ResponseDTO<GetQuestionDTO> updateQuestion(UpdateQuestionDTO dto) throws ResponseStatusException {
        var response = new ResponseDTO<GetQuestionDTO>();
        
        try {
            Optional<QuestionEntity> optQuestionEntity = this.questionRepository.findById(
                dto.getId()
            );

            QuestionEntity questionEntity = optQuestionEntity.orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, 
                    "Такого вопроса не существует!"
                )
            );

            TestEntity testEntity = questionEntity.getTest();

            UserEntity userEntity = this.userService.getCurrentUser();
            
            Optional<TestEntity> optTestEntity = this.testRepository.findTestEntityByUserAndId(userEntity, testEntity.getId());

            optTestEntity.orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, 
                    "Такого вопроса не существует!"
                )
            );

            questionEntity.setName(
                dto.getName()
            );

            this.questionRepository.save(questionEntity);

            var questionDTO = new GetQuestionDTO(
                questionEntity.getId(), 
                questionEntity.getName(), 
                new ArrayList<>()
            );

            this.answerRepository.removeAnswerEntityByQuestion(questionEntity);

            for(UpdateAnswerDTO answerDTO : dto.getAnswers()) {
                AnswerEntity answerEntity = this.answerMapper.toEntity(answerDTO);

                answerEntity.setQuestion(questionEntity);

                this.answerRepository.save(answerEntity);

                GetAnswerDTO getAnswerDTO = this.answerMapper.toGetAnswerDTO(answerEntity);

                questionDTO.getAnswers()
                           .add(getAnswerDTO);
            }

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

    @Override
    @Transactional
    public void deleteQuestion(Long questionId) throws ResponseStatusException {
        try {
            Optional<QuestionEntity> optQuestionEntity = this.questionRepository.findById(
                questionId
            );

            QuestionEntity questionEntity = optQuestionEntity.orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, 
                    "Такого вопроса не существует!"
                )
            );

            TestEntity testEntity = questionEntity.getTest();

            UserEntity userEntity = this.userService.getCurrentUser();
            
            Optional<TestEntity> optTestEntity = this.testRepository.findTestEntityByUserAndId(userEntity, testEntity.getId());

            optTestEntity.orElseThrow(
                () -> new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, 
                    "Такого вопроса не существует!"
                )
            );

            this.answerRepository.removeAnswerEntityByQuestion(questionEntity);

            this.questionRepository.deleteById(questionId);
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR, 
                "Ошибка при работе с базой данных!"
            );
        }
    }
}
