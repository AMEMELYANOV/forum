package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.ImplCommentService;
import ru.job4j.forum.service.ImplPostService;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * Тест класс реализации контроллеров
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.controller.PostController
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class PostControllerTest {

    /**
     * Объект заглушка направления запросов
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Объект заглушка для ImplPostService
     */
    @MockBean
    private ImplPostService postService;

    /**
     * Объект заглушка для ImplCommentService
     */
    @MockBean
    private ImplCommentService commentService;

    /**
     * Выполняется проверка возвращения страницы редактирования поста.
     */
    @Test
    @WithMockUser
    public void shouldReturnEditPost() throws Exception {
        this.mockMvc.perform(get("/edit").param("postId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    /**
     * Выполняется проверка возвращения страницы создания поста.
     */
    @Test
    @WithMockUser
    public void shouldReturnAddPost() throws Exception {
        this.mockMvc.perform(get("/addPost"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("edit"));
    }

    /**
     * Выполняется проверка возвращения страницы подробной информации о посте.
     */
    @Test
    @WithMockUser
    public void shouldReturnPost() throws Exception {
        this.mockMvc.perform(get("/post").param("postId", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
    }

    /**
     * Выполняется проверка редиректа на стартовую страницу и вызов метода сервисного
     * слоя {@link ImplPostService#save(Post)} аргументами запроса.
     */
    @Test
    @WithMockUser
    public void shouldSavePost() throws Exception {
        this.mockMvc.perform(post("/save")
                        .param("name", "Куплю ладу-грант. Дорого.")
                        .param("description", "Год выпуска не ранее 2018"))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
        ArgumentCaptor<Post> argument = ArgumentCaptor.forClass(Post.class);
        verify(postService).save(argument.capture());
        assertThat(argument.getValue().getName(), is("Куплю ладу-грант. Дорого."));
        assertThat(argument.getValue().getDescription(), is("Год выпуска не ранее 2018"));
    }

    /**
     * Выполняется проверка редиректа на страницу с подробной информацией о посте
     * и вызов метода сервисного слоя {@link ImplCommentService#addCommentToPost(int, Comment)}
     * с аргументами запроса.
     */
    @Test
    @WithMockUser
    public void shouldSaveCommentary() throws Exception {
        this.mockMvc.perform(post("/addComm")
                        .param("postId", "1")
                        .param("text", "Куплю, но не дорого"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("post"));
        ArgumentCaptor<Comment> argumentComment = ArgumentCaptor.forClass(Comment.class);
        ArgumentCaptor<Integer> argumentID = ArgumentCaptor.forClass(Integer.class);
        verify(commentService).addCommentToPost(argumentID.capture(), argumentComment.capture());
        assertThat(argumentID.getValue(), is(1));
        assertThat(argumentComment.getValue().getText(), is("Куплю, но не дорого"));
    }
}