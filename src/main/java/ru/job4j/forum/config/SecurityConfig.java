package ru.job4j.forum.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Конфигурация системы безопасности
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Шифратор паролей
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Источник данных
     */
    @Qualifier("dataSource")
    @Autowired
    private DataSource ds;

    /**
     * Создание бина PasswordEncoder для
     * шифрования паролей пользователей.
     *
     * @return объект BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Организует взаимодействие между системой безопасности и хранилища
     * пользователей для выполнения аутентификации.
     * Реализация использует менеджер аутентификации через JDBC.
     *
     * @param auth объект для аутентификации пользователей
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(ds)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from users "
                        + "where username = ?")
                .authoritiesByUsernameQuery(
                        " select u.username, a.authority "
                                + "from authorities as a, users as u "
                                + "where u.username = ? and u.authority_id = a.id");
    }

    /**
     * Создает конфигурацию авторизации пользователей при работе с приложением.
     *
     * @param http объект HttpSecurity для которого выполняется настройка авторизации
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/reg")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

}