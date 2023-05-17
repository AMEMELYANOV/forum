package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Post;

import java.util.List;

/**
 * Хранилище постов
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Post
 */
public interface PostRepository extends CrudRepository<Post, Long> {

    /**
     * Возвращает пост по идентификатору.
     *
     * @param id идентификатор поста
     * @return пост
     */
    Post findById(int id);

    /**
     * Возвращает список всех постов.
     *
     * @return список постов
     */
    @Override
    @Query(value = "select p from Post as p order by p.id")
    List<Post> findAll();
}
