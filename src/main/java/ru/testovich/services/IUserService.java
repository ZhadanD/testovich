package ru.testovich.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.testovich.entities.UserEntity;

public interface IUserService {
    UserDetailsService userDetailsService();

    UserEntity getByUsername(String username) throws UsernameNotFoundException;

    UserEntity getCurrentUser();
}
