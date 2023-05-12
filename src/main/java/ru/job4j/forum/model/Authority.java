package ru.job4j.forum.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

/**
 * Модель данных роль
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
@Table(name = "authorities")
public class Authority {

    /**
     * Идентификатор роли
     */
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    /**
     * Наименование роли
     */
    private String authority;
}