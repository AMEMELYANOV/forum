# Forum

## <p id="contents">Оглавление</p>

<ul>
<li><a href="#01">Описание проекта</a></li>
<li><a href="#02">Стек технологий</a></li>
<li><a href="#03">Требования к окружению</a></li>
<li><a href="#04">Сборка и запуск проекта</a>
    <ol type="1">
        <li><a href="#0401">Сборка проекта</a></li>
        <li><a href="#0402">Запуск проекта</a></li>
    </ol>
</li>
<li><a href="#05">Взаимодействие с приложением</a>
    <ol  type="1">
        <li><a href="#0501">Страница регистрации</a></li>
        <li><a href="#0502">Страница входа</a></li>
        <li><a href="#0503">Список всех тем</a></li>
        <li><a href="#0504">Страница создания и редактирования темы</a></li>
        <li><a href="#0505">Просмотр конкретной темы</a></li>
        <li><a href="#0506">Выход из приложения</a></li>
    </ol>
</li>
<li><a href="#todo">TODO list</a></li>
<li><a href="#contacts">Контакты</a></li>
</ul>

## <p id="01">Описание проекта</p>

Веб приложение, реализующее функционал простого форума.

Функционал:

* Регистрация пользователей, аутентификация и
  авторизация с использованием Spring Security.
* Добавление тем форума и комментариев.
* По каждой теме форума учитывается: пользователь, дата создания.
* Возможность отображения всех тем плюс комментарии.

<p><a href="#contents">К оглавлению</a></p>

## <p id="02">Стек технологий</p>

- Java 14
- HTML, Bootstrap 4, JSP + JSTL
- Spring Boot 2.7, Spring MVC, Spring Security, Spring Data
- PostgreSQL 14, Liquibase 4
- JUnit 4, Mockito
- Lombok
- Maven 3.8

Инструменты:
- Javadoc, JaCoCo, Checkstyle

<p><a href="#contents">К оглавлению</a></p>

## <p id="03">Требования к окружению</p>

Java 14, Maven 3.8, PostgreSQL 14

<p><a href="#contents">К оглавлению</a></p>

## <p id="04">Сборка и запуск проекта</p>

Для выполнения действий данного раздела необходимо установить
и настроить систему сборки проектов Maven.
По умолчании проект компилируется и собирается в директорию target.

<p><a href="#contents">К оглавлению</a></p>

### <p id="0401">1. Сборка проекта</p>

Команда для сборки в war
`mvn clean package -DskipTests`

<p><a href="#contents">К оглавлению</a></p>

### <p id="0402">2. Запуск проекта</p>

Команда для запуска
`mvn spring-boot:run`

<p><a href="#contents">К оглавлению</a></p>

## <p id="05">Взаимодействие с приложением</p>

### <p id="0501">1. Страница регистрации</p>

Каждому пользователю присваивается: имя пользователя и пароль.

![alt text](images/forum_img_2.jpg)

<p><a href="#contents">К оглавлению</a></p>

### <p id="0502">2. Страница входа</p>

Для входа необходимо ввести пароль и имя пользователя.

![alt text](images/forum_img_1.jpg)

<p><a href="#contents">К оглавлению</a></p>

### <p id="0503">3. Список всех тем</p>

На странице выводится список всех тем пользователей сайта.

![alt text](images/forum_img_3.jpg)

<p><a href="#contents">К оглавлению</a></p>

### <p id="0504">4. Страница создания и редактирования темы</p>

На странице задается название темы и описание темы, либо редактируется.

![alt text](images/forum_img_4.jpg)

![alt text](images/forum_img_5.jpg)

<p><a href="#contents">К оглавлению</a></p>

### <p id="0505">5. Просмотр конкретной темы</p>

На странице выводится тема и список комментариев.
Возможно добавление нового комментария.

![alt text](images/forum_img_6.jpg)

<p><a href="#contents">К оглавлению</a></p>

### <p id="0506">6. Выход из приложения</p>

При нажатии в панели навигации на ссылку "Выйти", происходит
выход пользователя из приложения с перенаправлением на страницу входа и
сообщением о том, что пользователь вышел.

![alt text](images/forum_img_7.jpg)

<p><a href="#contents">К оглавлению</a></p>

## <p id="todo">TODO list</p>

* Добавить JavaDoc
* Добавить страницу вывода моих тем
* Добавить возможность удаления тем

<p><a href="#contents">К оглавлению</a></p>

## <p id="contacts">Контакты</p>

[![alt-text](https://img.shields.io/badge/-telegram-grey?style=flat&logo=telegram&logoColor=white)](https://t.me/T_AlexME)&nbsp;&nbsp;
[![alt-text](https://img.shields.io/badge/@%20email-005FED?style=flat&logo=mail&logoColor=white)](mailto:amemelyanov@yandex.ru)&nbsp;&nbsp;

<p><a href="#contents">К оглавлению</a></p>