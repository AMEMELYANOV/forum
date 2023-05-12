package ru.job4j.forum.service;

import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityRepository;

/**
 * Сервис по работе с ролями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Authority
 */
public interface AuthorityService {

    /**
     * Возвращает роль по наименованию.
     * Для получения роли вызывается метод репозитория
     * {@link AuthorityRepository#findByAuthority(String)}.
     *
     * @param role наименование роли
     * @return роли
     */
    Authority findByAuthority(String role);
}
