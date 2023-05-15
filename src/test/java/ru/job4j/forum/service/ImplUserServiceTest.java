package ru.job4j.forum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

/**
 * Тест класс реализации сервисного слоя
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.ImplUserService
 */
@SpringBootTest(classes = Math.class)
class ImplUserServiceTest {

    /**
     * Объект заглушка для UserRepository
     */
    @MockBean
    private UserRepository userRepository;

    /**
     * Объект для доступа к методам UserService
     */
    private UserService userService;

    /**
     * Пользователь
     */
    private User user;

    /**
     * Создает необходимые для выполнения тестов общие объекты.
     * Создание выполняется перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1)
                .username("user")
                .password("pass")
                .build();
        userService = new ImplUserService(userRepository);
    }

    /**
     * Выполняется проверка возвращения пользователя при возврате
     * от userRepository, если пользователь найден по username.
     */
    @Test
    void findUserByUsernameShouldReturnUserWhenExists() {
        doReturn(user).when(userRepository).findUserByUsername(anyString());

        User userFromDB = userService.findUserByUsername(anyString());

        assertThat(userFromDB).isEqualTo(user);
    }

    /**
     * Выполняется проверка возвращения null при возврате
     * от userRepository, если пользователь не найден по username.
     */
    @Test
    void findUserByUsernameShouldReturnNullWhenNotExists() {
        doReturn(null).when(userRepository).findUserByUsername(anyString());

        User userFromDB = userService.findUserByUsername(anyString());

        assertThat(userFromDB).isNull();
    }

    /**
     * Выполняется проверка возвращения пользователя при возврате
     * от userRepository, если пользователь сохранен.
     */
    @Test
    void saveShouldReturnUserWhenSuccess() {
        doReturn(user).when(userRepository).save(user);

        User userFromDB = userService.save(user);

        assertThat(userFromDB).isEqualTo(user);
        verify(userRepository).save(user);
    }

}