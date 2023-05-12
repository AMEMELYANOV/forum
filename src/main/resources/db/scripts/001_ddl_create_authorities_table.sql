CREATE TABLE IF NOT EXISTS authorities (
   id SERIAL PRIMARY KEY,
   authority VARCHAR(50) NOT NULL unique
);

COMMENT ON TABLE authorities IS 'Роли';
COMMENT ON COLUMN authorities.id IS 'Идентификатор роли';
COMMENT ON COLUMN authorities.authority IS 'Наименование роли';