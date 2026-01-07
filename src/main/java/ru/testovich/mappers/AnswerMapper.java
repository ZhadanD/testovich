package ru.testovich.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.testovich.dto.GetAnswerDTO;
import ru.testovich.dto.UpdateAnswerDTO;
import ru.testovich.entities.AnswerEntity;

@Mapper(componentModel = "spring")
public interface AnswerMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "question", ignore = true)
    AnswerEntity toEntity(UpdateAnswerDTO dto);

    GetAnswerDTO toGetAnswerDTO(AnswerEntity entity);
}
