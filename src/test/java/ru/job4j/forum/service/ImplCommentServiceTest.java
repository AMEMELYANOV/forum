package ru.job4j.forum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.CommentRepository;
import ru.job4j.forum.repository.PostRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Тест класс реализации сервисного слоя
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.ImplCommentService
 */
@SpringBootTest(classes = Math.class)
class ImplCommentServiceTest {

    /**
     * Объект заглушка для CommentRepository
     */
    @MockBean
    private CommentRepository commentRepository;

    /**
     * Объект заглушка для UserRepository
     */
    @MockBean
    private UserRepository userRepository;

    /**
     * Объект заглушка для PostRepository
     */
    @MockBean
    private PostRepository postRepository;

    /**
     * Объект для доступа к методам CommentService
     */
    private CommentService commentService;

    /**
     * Комментарий
     */
    private Comment comment;

    /**
     * Пользователь
     */
    private User user;

    /**
     * Пост
     */
    private Post post;

    /**
     * Аутентификация
     */
    private Authentication authentication;

    /**
     * Контекст безозасности
     */
    private SecurityContext securityContext;

    /**
     * Создает необходимые для выполнения тестов общие объекты.
     * Создание выполняется перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        authentication = mock(Authentication.class);
        securityContext = mock(SecurityContext.class);
        comment = Comment.builder()
                .id(1)
                .text("text")
                .build();
        user = User.builder()
                .username("user")
                .build();
        post = Post.builder()
                .id(1)
                .name("post")
                .build();
        commentService = new ImplCommentService(commentRepository, userRepository, postRepository);
    }

    /**
     * Выполняется проверка возвращения списка комментариев при возврате
     * от commentRepository, если объекты в репозитории хранятся.
     */
    @Test
    void findAllByPostIdReturnCommentsWhenExists() {
        List<Comment> comments = List.of(comment);
        doReturn(comments).when(commentRepository).findAllByPostId(anyInt());

        List<Comment> commentsFromDB = commentRepository.findAllByPostId(anyInt());

        assertThat(commentsFromDB.size()).isEqualTo(1);
        assertThat(commentsFromDB.get(0)).isEqualTo(comment);
    }

    /**
     * Выполняется проверка возвращения пустого списка комментариев при возврате
     * от commentRepository, если объекты в репозитории отсутствуют.
     */
    @Test
    void findAllByPostIdShouldReturnEmptyListWhenNotExists() {
        List<Comment> comments = new ArrayList<>();
        doReturn(comments).when(commentRepository).findAllByPostId(anyInt());

        List<Comment> commentsFromDB = commentService.findAllByPostId(anyInt());

        assertThat(commentsFromDB.size()).isEqualTo(0);
    }

    /**
     * Выполняется проверка сохранения комментария поста.
     */
    @Test
    void addCommentToPostShouldAddCommentWhenSuccess() {
        doReturn(authentication).when(securityContext).getAuthentication();
        doReturn(user.getUsername()).when(authentication).getName();
        SecurityContextHolder.setContext(securityContext);

        doReturn(user).when(userRepository).findUserByUsername(anyString());
        doReturn(post).when(postRepository).findById(anyInt());
        doReturn(comment).when(commentRepository).save(comment);

        Comment commentFromDB = commentService.addCommentToPost(post.getId(), comment);

        assertThat(commentFromDB.getPost()).isEqualTo(post);
        assertThat(commentFromDB.getUser()).isEqualTo(user);
        assertThat(commentFromDB.getText()).isEqualTo(comment.getText());
        verify(commentRepository).save(comment);
    }
}