package ru.job4j.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.PostRepository;

import javax.transaction.Transactional;

/**
 * Реализация сервиса по работе с ролями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.AuthorityService
 */
@AllArgsConstructor
@Service
public class ImplAuthorityService implements AuthorityService {

    /**
     * Объект для доступа к методам AuthorityRepository
     */
    private final AuthorityRepository authorityRepository;

    /**
     * Возвращает роль по наименованию.
     * Для получения роли вызывается метод репозитория
     * {@link AuthorityRepository#findByAuthority(String)}.
     *
     * @param role наименование роли
     * @return роли
     */
    @Transactional
    @Override
    public Authority findByAuthority(String role) {
        return authorityRepository.findByAuthority(role);
    }
}
