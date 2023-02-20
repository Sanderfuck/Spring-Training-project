
INSERT INTO role (name)
VALUES ('ADMIN');
INSERT INTO role (name)
VALUES ('USER');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        'admin@nixs.com', 'Zinedine', 'Zidan', '1992-01-01',
        SELECT id FROM role WHERE name = 'ADMIN');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb',
        'user@nixs.com', 'Cristiano', 'Ronaldo', '2000-01-01',
        SELECT id FROM role WHERE name = 'USER');
