package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Authority;

/**
 * Хранилище ролей
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Authority
 */
public interface AuthorityRepository extends CrudRepository<Authority, Integer> {

    /**
     * Возвращает роль по наименованию роли.
     *
     * @param authority наименование роли
     * @return роль
     */
    Authority findByAuthority(String authority);
}