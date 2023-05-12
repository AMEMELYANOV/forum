INSERT INTO users (username, enabled, password, authority_id)
VALUES ('root', true, '$2a$10$S76VzFIaAyjOVHe7LauAe.xnAG6jtL//4ZLUhjzTPBNKuQmfNrT6W',
(SELECT id FROM authorities WHERE authority = 'ROLE_ADMIN'));