package ru.job4j.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.CommentRepository;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Реализация сервиса по работе с комментариями
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.CommentService
 */
@AllArgsConstructor
@Service
public class ImplCommentService implements CommentService {

    /**
     * Объект для доступа к методам CommentRepository
     */
    private final CommentRepository commentRepository;

    /**
     * Объект для доступа к методам UserRepository
     */
    private final UserRepository userRepository;

    /**
     * Объект для доступа к методам PostRepository
     */
    private final PostRepository postRepository;

    /**
     * Возвращает список комментариев.
     * Выполняет вызов метода {@link CommentRepository#findAllByPostId(int)}
     * для получения списка комментариев по идентификатору поста из репозитория.
     *
     * @param id идентификатор комментария
     * @return список комментариев
     */
    @Transactional
    @Override
    public List<Comment> findAllByPostId(int id) {
        return commentRepository.findAllByPostId(id);
    }

    /**
     * Сохраняет комментарий в репозитории.
     * Устанавливает в качестве полей комментария пользователя и пост.
     * Пользователь получается по имени пользователя контекста безопасности
     * из репозитория с помощью метода репозитория
     * {@link UserRepository#findUserByUsername(String)}.
     * Пост получается по аргументу id из репозитория с помощью метода
     * репозитория {@link PostRepository#findById(int)}
     *
     * @param id      идентификатор поста
     * @param comment комментарий
     */
    @Transactional
    @Override
    public void addCommentToPost(int id, Comment comment) {
        User user = userRepository.findUserByUsername(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());
        comment.setUser(user);
        comment.setPost(postRepository.findById(id).get());
        commentRepository.save(comment);
    }
}
