package ru.job4j.forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.ImplPostService;
import ru.job4j.forum.service.PostService;

/**
 * Контроллер индексной страницы
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@AllArgsConstructor
@Controller
public class IndexController {

    /**
     * Объект для доступа к методам AccidentService
     */
    private final PostService postService;

    /**
     * Обрабатывает GET запрос, возвращает стартовую страницу приложения.
     * Страница содержит список всех постов, которые получаются с сервисного
     * слоя методом {@link ImplPostService#findAll()}.
     *
     * @param model модель
     * @return стартовую страница приложения
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("posts", postService.findAll());
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        return "index";
    }
}