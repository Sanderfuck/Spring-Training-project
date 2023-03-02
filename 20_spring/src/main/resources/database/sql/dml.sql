INSERT INTO role (name)
VALUES ('ADMIN');
INSERT INTO role (name)
VALUES ('USER');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('admin', '$2a$12$qOznGxQg2AZBQE4C0KkKjurU4tVpErOGDKrF/h71m2JQTMyxOnnNi',
        'admin@nixs.com', 'Zinedine', 'Zidan', '1992-01-01',
        SELECT id FROM role WHERE name = 'ADMIN');

INSERT INTO users (login, password, email, first_name, last_name, birthday, role_id)
VALUES ('user', '$2a$12$73Lxq6ErBSaIg3NAG91jtewWHh7kIGFpnA6vDwcISCHjKqVJ2tWA2',
        'user@nixs.com', 'Cristiano', 'Ronaldo', '2000-01-01',
        SELECT id FROM role WHERE name = 'USER');
