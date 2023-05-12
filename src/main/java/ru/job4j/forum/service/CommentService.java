package ru.job4j.forum.service;

import ru.job4j.forum.model.Comment;
import ru.job4j.forum.repository.CommentRepository;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.List;

/**
 * Сервис по работе с комментариями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Comment
 */
public interface CommentService {

    /**
     * Возвращает список комментариев.
     * Выполняет вызов метода {@link CommentRepository#findAllByPostId(int)}
     * для получения списка комментариев по идентификатору поста из репозитория.
     *
     * @param id идентификатор комментария
     * @return список комментариев
     */
    List<Comment> findAllByPostId(int id);

    /**
     * Сохраняет комментарий в репозитории.
     * Устанавливает в качестве полей комментария пользователя и пост.
     * Пользователь получается из репозитория с помощью метода
     * {@link UserRepository#findUserByUsername(String)}
     * по имени пользователя из контекста безопасности.
     * Пост получается по аргументу id из репозитория с помощью метода
     * {@link PostRepository#findById(int)}
     *
     * @param id      идентификатор поста
     * @param comment комментарий
     */
    void addCommentToPost(int id, Comment comment);
}
