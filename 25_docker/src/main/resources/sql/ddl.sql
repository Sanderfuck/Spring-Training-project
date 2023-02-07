CREATE TABLE "user"
(
    user_id  SERIAL PRIMARY KEY,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE "order"
(
    order_id   SERIAL PRIMARY KEY,
    user_id    INT REFERENCES "user" (user_id) ON DELETE CASCADE,
    order_date DATE NOT NULL
);

CREATE TABLE book
(
    book_id          SERIAL PRIMARY KEY,
    title            VARCHAR(150)  NOT NULL,
    publication_year INT          NOT NULL,
    price            DECIMAL(8, 2) NOT NULL
);

CREATE TABLE order_book
(
    order_book_id SERIAL PRIMARY KEY,
    order_id      INT REFERENCES "order" (order_id) ON DELETE CASCADE,
    book_id       INT REFERENCES book (book_id) ON DELETE CASCADE
);

CREATE TABLE author
(
    author_id  SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name  VARCHAR(50) NOT NULL
);

CREATE TABLE book_author
(
    book_id   INT REFERENCES book (book_id) ON DELETE CASCADE,
    author_id INT REFERENCES author (author_id) ON DELETE CASCADE,
    PRIMARY KEY (book_id, author_id)
);

CREATE TABLE role
(
    role_id   SERIAL PRIMARY KEY,
    role_name VARCHAR(20) NOT NULL UNIQUE
);

--ALTER TABLE QUERIES for adding FK
ALTER TABLE "user"
    ADD role_id INT REFERENCES role (role_id) ON DELETE CASCADE;

