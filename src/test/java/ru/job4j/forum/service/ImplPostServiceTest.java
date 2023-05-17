package ru.job4j.forum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Тест класс реализации сервисного слоя
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.ImplPostService
 */
@SpringBootTest(classes = Main.class)
class ImplPostServiceTest {

    /**
     * Объект заглушка для PostRepository
     */
    @MockBean
    private PostRepository postRepository;

    /**
     * Объект для доступа к методам PostService
     */
    private PostService postService;

    /**
     * Пост
     */
    private Post post;

    /**
     * Создает необходимые для выполнения тестов общие объекты.
     * Создание выполняется перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        post = Post.builder()
                .id(1)
                .name("post")
                .created(new Date(System.currentTimeMillis()))
                .build();
        postService = new ImplPostService(postRepository);
    }

    /**
     * Выполняется проверка возвращения списка постов при возврате
     * от postRepository, если объекты в репозитории хранятся.
     */
    @Test
    void findAllShouldReturnPostsWhenExists() {
        List<Post> posts = List.of(post);
        doReturn(posts).when(postRepository).findAll();

        List<Post> postsFromDB = postRepository.findAll();

        assertThat(postsFromDB.size()).isEqualTo(1);
        assertThat(postsFromDB.get(0)).isEqualTo(post);
    }

    /**
     * Выполняется проверка возвращения пустого списка постов при возврате
     * от postRepository, если объекты в репозитории отсутствуют.
     */
    @Test
    void findAllShouldReturnEmptyListWhenNotExists() {
        List<Post> posts = new ArrayList<>();
        doReturn(posts).when(postRepository).findAll();

        List<Post> postsFromDB = postRepository.findAll();

        assertThat(postsFromDB.size()).isEqualTo(0);
    }

    /**
     * Выполняется проверка сохранения поста при возврате
     * от postRepository, если пост удачно сохранен.
     */
    @Test
    void saveShouldReturnPostWhenSuccess() {
        doReturn(post).when(postRepository).save(post);

        Post postFromDB = postRepository.save(post);

        assertThat(postFromDB).isEqualTo(post);
        verify(postRepository).save(post);
    }

    /**
     * Выполняется проверка возвращения поста при возврате
     * от postRepository, если пост найден по id.
     */
    @Test
    void findByIdShouldReturnPostWhenExists() {
        doReturn(post).when(postRepository).findById(anyInt());
        Post postFromDB = postRepository.findById(anyInt());

        assertThat(postFromDB).isEqualTo(post);
    }

    /**
     * Выполняется проверка возвращения null при возврате
     * от postRepository, если пост не найден по id.
     */
    @Test
    void findByIdShouldReturnNullWhenNotExists() {
        doReturn(null).when(postRepository).findById(anyInt());

        Post postFromDB = postRepository.findById(anyInt());

        assertThat(postFromDB).isNull();
    }
}