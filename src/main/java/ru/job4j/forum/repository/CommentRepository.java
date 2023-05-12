package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.forum.model.Comment;

import java.util.List;

/**
 * Хранилище комментариев
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Comment
 */
public interface CommentRepository extends CrudRepository<Comment, Long> {

    /**
     * Возвращает список комментариев по
     * идентификатору поста.
     *
     * @param id идентификатор поста
     * @return список комментариев
     */
    List<Comment> findAllByPostId(int id);

}
