package ru.testovich.services;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ru.testovich.entities.UserEntity;
import ru.testovich.repositories.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements IUserService {
    private UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    @Override
    public UserEntity getByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = this.userRepository.findUserEntityByUsername(username);

        if(userEntity == null)
            throw new UsernameNotFoundException("Пользователь не найден");

        return userEntity;
    }

    @Override
    public UserEntity getCurrentUser() {
        return this.getByUsername(
            SecurityContextHolder
            .getContext()
            .getAuthentication()
            .getName()
        );
    }
}
