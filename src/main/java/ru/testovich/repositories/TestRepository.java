package ru.testovich.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.testovich.entities.TestEntity;
import ru.testovich.entities.UserEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    List<TestEntity> findTestEntityByUser(UserEntity user);

    Optional<TestEntity> findTestEntityByUserAndId(UserEntity user, Long testId);
}
