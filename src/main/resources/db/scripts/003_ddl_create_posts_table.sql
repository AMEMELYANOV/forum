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