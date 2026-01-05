package ru.testovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.testovich.entities.QuestionEntity;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {
    
}
