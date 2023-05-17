package ru.job4j.forum.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.AuthorityService;
import ru.job4j.forum.service.UserService;

/**
 * Контроллер для работы с регистрацией пользователей
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@AllArgsConstructor
@Controller
public class RegController {

    /**
     * Шифратор паролей
     */
    private final PasswordEncoder encoder;

    /**
     * Объект для доступа к методам UserService
     */
    private final UserService userService;

    /**
     * Объект для доступа к методам AuthorityService
     */
    private final AuthorityService authorityService;

    /**
     * Обрабатывает POST запрос. При удачном сохранении пользователя в репозитории,
     * выполняется перенаправление на страницу входа, при неудачной регистрации
     * возвращает пользователя на страницу регистрации с сообщением об ошибке.
     * Для проверки наличия пользователя в репозитории используется метод
     * сервисного слоя {@link UserService#findUserByUsername(String)}.
     * Для присвоения роли пользователю используется метод сервисного слоя
     * {@link AuthorityService#findByAuthority(String)}.
     * Для сохранения пользователя используется метод сервисного слоя
     * {@link UserService#save(User)}.
     *
     * @param user  пользователь сформированный из данных формы регистрации
     * @param model модель
     * @return страница входа пользователя
     */
    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {
        User userFromDB = userService.findUserByUsername(user.getUsername());
        if (userFromDB != null) {
            String errorMessage = "This username exists!";
            model.addAttribute("errorMessage", errorMessage);
            return "reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorityService.findByAuthority("ROLE_USER"));
        userService.save(user);
        return "redirect:/login";
    }

    /**
     * Обрабатывает GET запрос, возвращает страницу регистрации пользователя.
     *
     * @return страница регистрации пользователя
     */
    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }
}