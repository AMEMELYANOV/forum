package ru.job4j.forum.model;

import lombok.*;

import javax.persistence.*;

/**
 * Модель данных пользователь
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "users")
public class User {

    /**
     * Идентификатор пользователя
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Пароль пользователя
     */
    private String password;

    /**
     * Имя пользователя
     */
    private String username;

    /**
     * Статус учетной записи пользователя
     */
    private boolean enabled;

    /**
     * Роль
     */
    @ManyToOne
    @JoinColumn(name = "authority_id")
    private Authority authority;
}