package ru.job4j.forum.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.job4j.forum.Main;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.repository.AuthorityRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;

/**
 * Тест класс реализации сервисного слоя
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.service.ImplAuthorityService
 */
@SpringBootTest(classes = Main.class)
class ImplAuthorityServiceTest {

    /**
     * Объект заглушка для AuthorityRepository
     */
    @MockBean
    private AuthorityRepository authorityRepository;

    /**
     * Объект для доступа к методам AuthorityService
     */
    private AuthorityService authorityService;

    /**
     * Роль
     */
    private Authority authority;

    /**
     * Создает необходимые для выполнения тестов общие объекты.
     * Создание выполняется перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        authority = Authority.builder()
                .id(1)
                .authority("auth")
                .build();
        authorityService = new ImplAuthorityService(authorityRepository);
    }

    /**
     * Выполняется проверка возвращения роли при возврате
     * от authorityRepository, если роль найдена по authority.
     */
    @Test
    void findByAuthorityShouldReturnAuthorityWhenExists() {
        doReturn(authority).when(authorityRepository).findByAuthority(anyString());

        Authority authorityFromDB = authorityService.findByAuthority(anyString());

        assertThat(authorityFromDB).isEqualTo(authority);
    }

    /**
     * Выполняется проверка возвращения null при возврате
     * от authorityRepository, если роль не найдена по authority.
     */
    @Test
    void findByAuthorityShouldReturnNullWhenNotExists() {
        doReturn(null).when(authorityRepository).findByAuthority(anyString());

        Authority authorityFromDB = authorityService.findByAuthority(anyString());

        assertThat(authorityFromDB).isNull();
    }
}