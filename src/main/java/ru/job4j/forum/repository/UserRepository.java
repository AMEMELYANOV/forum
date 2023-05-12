package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.User;

/**
 * Хранилище пользователей
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.User
 */
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * Возвращает пользователя по имени.
     *
     * @param username имя пользователя
     * @return пользователь
     */
    User findUserByUsername(String username);
}
