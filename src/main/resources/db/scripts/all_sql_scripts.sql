CREATE TABLE IF NOT EXISTS authorities (
   id SERIAL PRIMARY KEY,
   authority VARCHAR(50) NOT NULL unique
);

COMMENT ON TABLE authorities IS 'Роли';
COMMENT ON COLUMN authorities.id IS 'Идентификатор роли';
COMMENT ON COLUMN authorities.authority IS 'Наименование роли';

---------------------------------------------------------------

CREATE TABLE IF NOT EXISTS users (
   id SERIAL PRIMARY KEY,
   username VARCHAR(50) NOT NULL UNIQUE,
   password VARCHAR(100) NOT NULL,
   enabled BOOLEAN DEFAULT TRUE,
   authority_id INT NOT NULL REFERENCES authorities(id)
);

COMMENT ON TABLE users IS 'Пользователи';
COMMENT ON COLUMN users.id IS 'Идентификатор пользователя';
COMMENT ON COLUMN users.username IS 'Имя пользователя';
COMMENT ON COLUMN users.password IS 'Пароль пользователя';
COMMENT ON COLUMN users.enabled IS 'Статус пользователя';
COMMENT ON COLUMN users.authority_id IS 'Ссылка на роль';

---------------------------------------------------------------

CREATE TABLE IF NOT EXISTS posts (
   id SERIAL PRIMARY KEY,
   name VARCHAR,
   description TEXT,
   created TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
   user_id INT REFERENCES users(id)
);

COMMENT ON TABLE posts IS 'Пост';
COMMENT ON COLUMN posts.id IS 'Идентификатор поста';
COMMENT ON COLUMN posts.name IS 'Наименование поста';
COMMENT ON COLUMN posts.description IS 'Описание поста';
COMMENT ON COLUMN posts.created IS 'Дата и время создания поста';
COMMENT ON COLUMN posts.user_id IS 'Ссылка на пользователя';

---------------------------------------------------------------

CREATE TABLE IF NOT EXISTS comments (
    id SERIAL PRIMARY KEY,
    text TEXT NOT NULL,
    created TIMESTAMP  WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    user_id INT REFERENCES users(id),
    post_id INT REFERENCES posts(id)
);

COMMENT ON TABLE comments IS 'Комментарии';
COMMENT ON COLUMN comments.id IS 'Идентификатор комментария';
COMMENT ON COLUMN comments.text IS 'Содержание комментария';
COMMENT ON COLUMN comments.created IS 'Дата и время написания комментария';
COMMENT ON COLUMN comments.user_id IS 'Ссылка на пользователя';
COMMENT ON COLUMN comments.post_id IS 'Ссылка на пост';

---------------------------------------------------------------

INSERT INTO authorities (authority) VALUES ('ROLE_USER');
INSERT INTO authorities (authority) VALUES ('ROLE_ADMIN');

---------------------------------------------------------------

INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', true, '$2a$10$S76VzFIaAyjOVHe7LauAe.xnAG6jtL//4ZLUhjzTPBNKuQmfNrT6W',
(SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));

---------------------------------------------------------------

INSERT INTO posts (name, description, user_id) VALUES ('О чем этот форум?', 'Форум на свободную тему...', 1);
INSERT INTO posts (name, description, user_id) VALUES ('Правила форума.', 'За правилами форума обратитесь к администрации', 1);

---------------------------------------------------------------

INSERT INTO comments (text, user_id, post_id) VALUES ('2', 1, 1);
INSERT INTO comments (text, user_id, post_id) VALUES ('3', 1, 1);
INSERT INTO comments (text, user_id, post_id) VALUES ('4', 1, 2);
INSERT INTO comments (text, user_id, post_id) VALUES ('5', 1, 2);