package ru.job4j.forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Comment;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.CommentService;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

/**
 * Контроллер для работы с постами
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.model.Post
 */
@AllArgsConstructor
@Controller
public class PostController {

    /**
     * Объект для доступа к методам AccidentService
     */
    private final PostService postService;

    /**
     * Объект для доступа к методам AccidentService
     */
    private final CommentService commentService;

    /**
     * Объект для доступа к методам AccidentService
     */
    private final UserService userService;

    /**
     * Обрабатывает GET запрос, возвращает страницу редактирования поста
     * по аргументу идентификатора поста. На страницу редактирования помещаются
     * данные по выбранному для редактирования поста через model. Пост
     * получается через метод сервисного слоя {@link PostService#findById(int)}.
     *
     * @param postId идентификатор поста
     * @param model  модель
     * @return страница редактирования поста
     */
    @GetMapping("/edit")
    public String editPost(@RequestParam("postId") int postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("user",
                SecurityContextHolder
                        .getContext()
                        .getAuthentication()
                        .getPrincipal());
        return "edit";
    }

    /**
     * Обрабатывает GET запрос, возвращает страницу создания поста.
     * На страницу редактирования помещаются данные объекта пустого поста
     * через model.
     *
     * @param model модель
     * @return страница создания поста
     */
    @GetMapping("/addPost")
    public String addPost(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "edit";
    }

    /**
     * Обрабатывает GET запрос, возвращает страницу подробной информации о посте
     * по аргументу идентификатора поста. На страницу подробной информации помещаются
     * данные по выбранному для посту через model. Пост
     * получается через метод сервисного слоя {@link PostService#findById(int)}.
     * В модель так же добавляется список комментариев, который получается с
     * помощью метода сервисного слоя {@link CommentService#findAllByPostId(int)}.
     *
     * @param postId идентификатор поста
     * @param model  модель
     * @return страница подробной информации о посте
     */
    @GetMapping("/post")
    public String viewPost(@RequestParam("postId") int postId, Model model) {
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.findAllByPostId(postId));
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "post";
    }

    /**
     * Обрабатывает POST запрос, выполняется сохранение пользователя в репозитории
     * и перенаправление пользователя на стартовую страницу приложения.
     * В пост сохраняются данные о текущем пользователе, которые получаются из
     * текущего контекста безопасности и с помощью метода сервисного слоя
     * {@link UserService#findUserByUsername(String)}.
     * Пост сохраняется с помощью метода сервисного слоя {@link PostService#save(Post)}.
     *
     * @param post пост
     * @return редирект на стартовую страницу приложения
     */
    @PostMapping("/save")
    public String savePost(@ModelAttribute Post post) {
        String username = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();
        User user = userService.findUserByUsername(username);
        post.setUser(user);
        postService.save(post);
        return "redirect:/index";
    }

    /**
     * Обрабатывает POST запрос, выполняется сохранение комментария в репозитории
     * и возврат страницы с подробной информацией о посте.
     * В пост сохраняются данные о комментарии, переданные в запросе.
     * Пост для сохранения получается с помощью аргумента идентификатора и метода
     *
     * @param postId  идентификатор поста
     * @param comment комментарий
     * @param model   модель
     * @return страница подробной информации о посте
     */
    @PostMapping("/addComm")
    public String addCommentary(@RequestParam("postId") int postId,
                                @ModelAttribute Comment comment, Model model) {
        commentService.addCommentToPost(postId, comment);
        Post post = postService.findById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentService.findAllByPostId(postId));
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "post";
    }
}