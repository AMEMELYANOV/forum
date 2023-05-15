package ru.job4j.forum.service;

import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

/**
 * Сервис по работе с пользователями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.User
 */
public interface UserService {

    /**
     * Сохраняет пользователя в репозитории.
     * Для сохранения пользователя вызывается метод репозитория
     * {@link UserRepository#save(Object)}
     *
     * @param user пользователь
     * @return пользователь
     */
    User save(User user);

    /**
     * Возвращает пользователя по имени пользователя.
     * Пользователь получается из репозитория с помощью метода
     * {@link UserRepository#findUserByUsername(String)}
     *
     * @param username имя пользователя
     * @return пользователь
     */
    User findUserByUsername(String username);
}
