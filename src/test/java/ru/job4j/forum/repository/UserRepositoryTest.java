package ru.job4j.forum.repository;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Тест класс репозитория
 *
 * @author Alexander Emelyanov
 * @version 1.0
 * @see ru.job4j.forum.repository.UserRepository
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    /**
     * Объект для доступа к методам UserRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Объект для доступа к методам AuthorityRepository
     */
    @Autowired
    private AuthorityRepository authorityRepository;

    /**
     * Выполняется проверка нахождения в репозитории пользователя по имени,
     * если пользователь сохранен в репозитории.
     */
    @Test
    public void findUserByUsernameShouldReturnUserWhenExists() {
        Authority authority = Authority.builder().id(1).authority("authority").build();
        authority = authorityRepository.save(authority);
        User user = User.builder()
                .username("user")
                .password("pass")
                .authority(authority)
                .build();
        userRepository.save(user);
        User userFromDB = userRepository.findUserByUsername(user.getUsername());

        assertThat(userFromDB.getUsername()).isEqualTo(user.getUsername());
    }

    /**
     * Выполняется проверка нахождения в репозитории пользователя по имени,
     * если пользователь не сохранен в репозитории.
     */
    @Test
    public void findUserByUsernameShouldReturnNullWhenNotExists() {
        User userFromDB = userRepository.findUserByUsername("user");

        assertThat(userFromDB).isNull();
    }
}