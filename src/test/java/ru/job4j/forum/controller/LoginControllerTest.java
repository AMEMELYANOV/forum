package ru.job4j.forum.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тест класс реализации контроллеров
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see LoginController
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class LoginControllerTest {

    /**
     * Объект заглушка направления запросов
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     * Выполняется проверка возвращения страницы входа пользователя.
     */
    @Test
    @WithMockUser
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    /**
     * Выполняется проверка редиректа на страницу входа с параметром
     * logout=true.
     */
    @Test
    @WithMockUser
    public void shouldRedirectWithLogoutTrue() throws Exception {
        this.mockMvc.perform(get("/logout"))
                .andDo(print())
                .andExpect(redirectedUrl("/login?logout=true"));
    }
}