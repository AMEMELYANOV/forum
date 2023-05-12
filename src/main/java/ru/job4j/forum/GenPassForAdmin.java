package ru.job4j.forum;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Генератор пароля для администратора
 *
 * @author Alexander Emelyanov
 * @version 1.0
 */
public class GenPassForAdmin {

    /**
     * Выполняет кодирование паролей
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("secret");
        System.out.println(pwd);
    }
}
