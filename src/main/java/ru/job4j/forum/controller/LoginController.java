package ru.job4j.forum.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Контроллер для работы с аутентификацией пользователя
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Controller
public class LoginController {

    /**
     * Обрабатывает GET запрос, возвращает страницу входа пользователя.
     * В зависимости от параметров error и logout на страницу будут выведены сообщения
     * для пользователя о необходимости исправить вводимые данные.
     *
     * @param error  параметр GET запроса, true, если есть ошибка при заполнении формы
     * @param logout параметр GET запроса, true, если пользователь разлогинился
     * @param model  модель
     * @return страница входа пользователя
     */
    @GetMapping("/login")
    public String loginPage(@RequestParam(value = "error", required = false) String error,
                            @RequestParam(value = "logout", required = false) String logout,
                            Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Обрабатывает GET запрос, перенаправляет на страницу входа.
     * Выполняется выход пользователя из приложения и
     * очистка контекста безопасности.
     *
     * @param request  запрос клиента
     * @param response ответ клиенту
     * @return перенаправление на страницу входа с параметром logout=true
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }
}
