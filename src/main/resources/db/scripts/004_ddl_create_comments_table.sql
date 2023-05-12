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