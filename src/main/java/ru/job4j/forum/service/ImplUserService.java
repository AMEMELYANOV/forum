package ru.job4j.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

import javax.transaction.Transactional;

/**
 * Реализация сервиса по работе с пользователями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.UserService
 */
@AllArgsConstructor
@Service
public class ImplUserService implements UserService {

    /**
     * Объект для доступа к методам UserRepository
     */
    private final UserRepository userRepository;

    /**
     * Сохраняет пользователя в репозитории.
     * Для сохранения пользователя вызывается метод репозитория
     * {@link UserRepository#save(Object)}
     *
     * @param user пользователь
     * @return пользователь
     */
    @Transactional
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * Возвращает пользователя по имени пользователя.
     * Пользователь получается из репозитория с помощью метода
     * {@link UserRepository#findUserByUsername(String)}
     *
     * @param username имя пользователя
     * @return пользователь
     */
    @Transactional
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
