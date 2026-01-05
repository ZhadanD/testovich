package ru.testovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.testovich.entities.AnswerEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    
}
