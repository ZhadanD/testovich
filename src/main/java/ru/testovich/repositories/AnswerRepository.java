package ru.testovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.testovich.entities.AnswerEntity;
import ru.testovich.entities.QuestionEntity;

public interface AnswerRepository extends JpaRepository<AnswerEntity, Long> {
    void removeAnswerEntityByQuestion(QuestionEntity questionEntity);
}
