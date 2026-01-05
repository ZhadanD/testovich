package ru.testovich.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import ru.testovich.dto.CreateQuestionDTO;
import ru.testovich.dto.GetAnswerDTO;
import ru.testovich.dto.GetQuestionDTO;
import ru.testovich.entities.AnswerEntity;
import ru.testovich.entities.QuestionEntity;

@Mapper(componentModel = "spring")
public interface QuestionMapper {
    default QuestionEntity toEntity(CreateQuestionDTO dto) {
        var questionEntity = new QuestionEntity();
        questionEntity.setName(
            dto.getName()
        );

        List<AnswerEntity> answerEntities = dto.getAnswers().stream().map(answerDTO -> {
            var answerEntity = new AnswerEntity();
            answerEntity.setName(
                answerDTO.getName()
            );
            answerEntity.setIsTrue(
                answerDTO.getIsTrue()
            );

            return answerEntity;
        }).toList();

        questionEntity.setAnswers(answerEntities);

        return questionEntity;
    }

    default GetQuestionDTO toGetQuestionDTO(QuestionEntity entity) {
        List<GetAnswerDTO> answers = entity.getAnswers().stream().map(
            answerEntity -> new GetAnswerDTO(
                answerEntity.getId(),
                answerEntity.getName(),
                answerEntity.getIsTrue()
            )
        ).toList();

        var questionDTO = new GetQuestionDTO(
            entity.getId(), 
            entity.getName(), 
            answers
        );

        return questionDTO;
    }
}
