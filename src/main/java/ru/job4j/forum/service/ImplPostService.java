package ru.job4j.forum.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Реализация сервиса по работе с постами
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.PostService
 */
@AllArgsConstructor
@Service
public class ImplPostService implements PostService {

    /**
     * Объект для доступа к методам PostRepository
     */
    private final PostRepository postRepository;

    /**
     * Возвращает список постов.
     * Выполняет вызов метода {@link PostRepository#findAll()}
     * для получения списка всех постов из репозитория.
     *
     * @return список постов
     */
    @Transactional
    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    /**
     * Сохраняет пост в репозитории.
     * Для сохранения поста вызывается метод репозитория
     * {@link PostRepository#save(Object)}.
     *
     * @param post пост
     * @return сохраненный пост
     */
    @Transactional
    @Override
    public Post save(Post post) {
        Post oldPost = findById(post.getId());
        if (oldPost != null) {
            post.setCreated(oldPost.getCreated());
        }
        return postRepository.save(post);
    }

    /**
     * Возвращает пост по аргументу id.
     * Для получения поста вызывается метод репозитория
     * {@link PostRepository#findById(int)}.
     *
     * @param id идентификатор поста
     * @return найденный пост
     */
    @Transactional
    @Override
    public Post findById(int id) {
        return postRepository.findById(id);
    }
}