
INSERT INTO role (name)
VALUES ('ADMIN');
INSERT INTO role (name)
VALUES ('USER');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin', 'password', 'admin@gmail.com', 'admin', 'admin', '1998-01-01',
        SELECT id FROM role WHERE name = 'ADMIN');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin2', 'password2', 'admin2@gmail.com', 'admin2', 'admin2', '1996-01-01',
        SELECT id FROM role WHERE name = 'ADMIN');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', 'password', 'user@gmail.com', 'user', 'user', '1999-01-01',
        SELECT id FROM role WHERE name = 'USER');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user1', 'password1', 'user1@gmail.com', 'user1', 'user1', '1993-01-01',
        SELECT id FROM role WHERE name = 'USER');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user2', 'password2', 'user2@gmail.com', 'user2', 'user2', '1994-01-01',
        SELECT id FROM role WHERE name = 'USER');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user3', 'password3', 'user3@gmail.com', 'user3', 'user3', '1995-01-01',
        SELECT id FROM role WHERE name = 'USER');
