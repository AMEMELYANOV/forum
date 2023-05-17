package ru.job4j.forum.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

/**
 * Модель данных комментарий
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
@Table(name = "comments")
public class Comment {

    /**
     * Идентификатор комментария
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Текст комментария
     */
    private String text;

    /**
     * Пользователь
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * Пост
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * Дата и время создания
     */
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date created = new Date(System.currentTimeMillis());
}