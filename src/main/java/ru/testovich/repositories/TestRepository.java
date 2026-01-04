package ru.testovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.testovich.entities.TestEntity;

public interface TestRepository extends JpaRepository<TestEntity, Long> {
    
}
