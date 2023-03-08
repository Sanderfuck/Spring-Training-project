DROP ALL OBJECTS;
CREATE TABLE IF NOT EXISTS role
(
    id   BIGINT GENERATED ALWAYS as identity PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id         BIGINT GENERATED ALWAYS as identity PRIMARY KEY,
    login      VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    birthday   DATE         NOT NULL,
    role_id    BIGINT       NOT NULL,
    FOREIGN KEY (role_id) REFERENCES role(id)
);
