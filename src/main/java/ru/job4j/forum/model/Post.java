package ru.job4j.forum.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Модель данных пост
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
@Table(name = "posts")
public class Post {

    /**
     * Идентификатор поста
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Наименование поста
     */
    private String name;

    /**
     * Содержание поста
     */
    private String description;

    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Дата и время создания
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());
}