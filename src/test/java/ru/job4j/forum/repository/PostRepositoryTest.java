package ru.job4j.forum.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.forum.model.Post;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тест класс репозитория
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.repository.PostRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class PostRepositoryTest {

    /**
     * Объект для доступа к методам PostRepository
     */
    @Autowired
    private PostRepository postRepository;

    /**
     * Выполняется проверка нахождения в репозитории поста по идентификатору,
     * если пост сохранен в репозитории.
     */
    @Test
    public void findPostByIdShouldReturnPostWhenExists() {
        Post post = Post.builder()
                .name("post")
                .created(new Date(System.currentTimeMillis()))
                .build();
        int id = postRepository.save(post).getId();
        Post postFromDB = postRepository.findById(id);

        assertThat(postFromDB.getName()).isEqualTo(post.getName());
    }

    /**
     * Выполняется проверка нахождения в репозитории поста по идентификатору,
     * если пост не сохранен в репозитории.
     */
    @Test
    public void findPostByIdShouldReturnNullWhenNotExists() {
        Post postFromDB = postRepository.findById(0);

        assertThat(postFromDB).isNull();
    }

    /**
     * Выполняется проверка нахождения в репозитории списка всех постов,
     * если посты сохранены в репозитории.
     */
    @Test
    public void findAllPostsShouldReturnPostsWhenExists() {
        Post post1 = Post.builder()
                .name("post1")
                .created(new Date(System.currentTimeMillis()))
                .build();
        Post post2 = Post.builder()
                .name("post2")
                .created(new Date(System.currentTimeMillis()))
                .build();
        post1 = postRepository.save(post1);
        post2 = postRepository.save(post2);

        List<Post> posts = postRepository.findAll();

        assertThat(posts.size()).isEqualTo(2);
        assertThat(posts.get(0).getName()).isEqualTo(post1.getName());
        assertThat(posts.get(1).getName()).isEqualTo(post2.getName());

    }
}