package ru.job4j.forum.service;

import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.List;

/**
 * Сервис по работе с постами
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Post
 */
public interface PostService {

    /**
     * Возвращает список постов.
     * Выполняет вызов метода {@link PostRepository#findAll()}
     * для получения списка всех постов из репозитория.
     *
     * @return список постов
     */
    List<Post> findAll();

    /**
     * Сохраняет пост в репозитории.
     * Для сохранения поста вызывается метод репозитория
     * {@link PostRepository#save(Object)}.
     *
     * @param post пост
     * @return сохраненный пост
     */
    Post save(Post post);

    /**
     * Возвращает пост по аргументу id.
     * Для получения поста вызывается метод репозитория
     * {@link PostRepository#findById(int)}.
     *
     * @param id идентификатор поста
     * @return найденный пост
     */
    Post findById(int id);
}