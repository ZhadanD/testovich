package ru.testovich.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.testovich.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findUserEntityByUsername(String username);
}
