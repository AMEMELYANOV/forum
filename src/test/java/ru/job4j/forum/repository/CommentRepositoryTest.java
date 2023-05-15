package ru.job4j.forum.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тест класс репозитория
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.repository.CommentRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CommentRepositoryTest {

    /**
     * Объект для доступа к методам CommentRepository
     */
    @Autowired
    private CommentRepository commentRepository;

    /**
     * Объект для доступа к методам PostRepository
     */
    @Autowired
    private PostRepository postRepository;

    /**
     * Выполняется проверка нахождения всех комментариев по идентификатору поста,
     * если комментарии сохранены в репозитории.
     */
    @Test
    public void findAllByPostIdShouldReturnCommentsWhenExists() {
        Post post = Post.builder()
                .name("post")
                .created(new Date(System.currentTimeMillis()))
                .build();
        post = postRepository.save(post);
        Comment comment1 = Comment.builder()
                .text("comment1")
                .post(post)
                .created(new Date(System.currentTimeMillis()))
                .build();
        Comment comment2 = Comment.builder()
                .text("comment2")
                .post(post)
                .created(new Date(System.currentTimeMillis()))
                .build();
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        List<Comment> comments = commentRepository.findAllByPostId(comment1.getPost().getId());

        assertThat(comments.size()).isEqualTo(2);
        assertThat(comments.get(0).getText()).isEqualTo(comment1.getText());
        assertThat(comments.get(1).getText()).isEqualTo(comment2.getText());
    }
}