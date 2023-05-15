package ru.job4j.forum.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.forum.model.Authority;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тест класс репозитория
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.repository.AuthorityRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AuthorityRepositoryTest {

    /**
     * Объект для доступа к методам AuthorityRepository
     */
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Выполняется проверка нахождения в репозитории роли по наименованию,
     * если роль сохранена в репозитории.
     */
    @Test
    public void findByAuthorityShouldReturnAuthorityWhenExists() {
        Authority authority = Authority.builder()
                .authority("authority")
                .build();
        authorityRepository.save(authority);
        Authority authorityFromDB = authorityRepository.findByAuthority(authority.getAuthority());

        assertThat(authorityFromDB.getAuthority()).isEqualTo(authority.getAuthority());
    }

    /**
     * Выполняется проверка нахождения в репозитории роли по наименованию,
     * если роль не сохранена в репозитории.
     */
    @Test
    public void findByAuthorityShouldReturnNullWhenNotExists() {
        Authority authorityFromDB = authorityRepository.findByAuthority("authority");

        assertThat(authorityFromDB).isNull();
    }

}