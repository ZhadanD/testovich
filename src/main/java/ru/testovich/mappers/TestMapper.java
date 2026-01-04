package ru.testovich.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ru.testovich.dto.CreateTestDTO;
import ru.testovich.dto.GetTestDTO;
import ru.testovich.entities.TestEntity;

@Mapper(componentModel = "spring")
public interface TestMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    TestEntity toEntity(CreateTestDTO dto);

    GetTestDTO toGetTestDTO(TestEntity entity);
}
