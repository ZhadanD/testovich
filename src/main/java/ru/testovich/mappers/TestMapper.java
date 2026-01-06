package ru.testovich.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetFullTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.entities.TestEntity;

@Mapper(componentModel = "spring")
public interface TestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "questions", ignore = true)
    TestEntity toEntity(CreateTestDTO dto);

    GetTestDTO toGetTestDTO(TestEntity entity);

    List<GetTestDTO> toListGetTestDTO(List<TestEntity> entities);

    GetFullTestDTO toGetFullTestDTO(TestEntity testEntity);
}
