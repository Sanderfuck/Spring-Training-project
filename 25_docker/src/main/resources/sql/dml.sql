INSERT INTO role (role_name)
VALUES ('administrator'), ('customer');

INSERT INTO "user" (username, password, email, role_id)
VALUES ('Wayne Rooney', 'password1', 'wayne.rooney@nix.com',
        (SELECT DISTINCT role_id FROM role WHERE role_name = 'administrator'));
INSERT INTO "user" (username, password, email, role_id) VALUES
('David Beckham', 'password2', 'david.beckham@nix.com',
 (SELECT role_id FROM role WHERE role_name = 'administrator'));
INSERT INTO "user" (username, password, email, role_id) VALUES
('Sergio Ramos', 'password3', 'sergio.ramos@nix.com',
 (SELECT role_id FROM role WHERE role_name = 'customer'));
INSERT INTO "user" (username, password, email, role_id) VALUES
('Lionel Messi', 'password4', 'lionel.messi@nix.com',
 (SELECT role_id FROM role WHERE role_name = 'customer'));
INSERT INTO "user" (username, password, email, role_id) VALUES
('Cristiano Ronaldo', 'password5', 'cristiano.ronaldo@nix.com',
 (SELECT role_id FROM role WHERE role_name = 'customer'));
INSERT INTO "user" (username, password, email, role_id) VALUES
('Gerard Pique', 'password6', 'gerard.pique@nix.com',
 (SELECT role_id FROM role WHERE role_name = 'customer'));

INSERT INTO "user" (username, password, email, role_id)
VALUES ('John Smith', 'password7', 'john.smith@nix.com',
        (SELECT role_id FROM role WHERE role_name = 'customer')),
       ('Mary Jane', 'password8', 'jane.doe@nix.com',
        (SELECT role_id FROM role WHERE role_name = 'administrator'));

-- Use DELETE and UPDATE what says in requirement for this task

DELETE FROM "user" WHERE username = 'Mary Jane';

UPDATE "user" SET username = 'Zinedine Zidane',
                  email = 'zinedine.zidane@nix.com' WHERE username = 'John Smith';


INSERT INTO author (first_name, last_name)
VALUES ('George', 'Orwell'),
       ('Ernest', 'Hemingway'),
       ('J.K.', 'Rowling'),
       ('Stephen', 'King'),
       ('Mark', 'Twain');

INSERT INTO book (title, publication_year, price)
VALUES ('1984', 1949, 15.00),
       ('Animals Farm', 1945, 12.00),
       ('The Old Man and the Sea', 1952, 13.00),
       ('Harry Potter and the Philosopher Stone', 1997, 20.00),
       ('Harry Potter and the Chamber of Secrets', 1998, 20.00),
       ('Harry Potter and the Prisoner of Azkaban', 1999, 20.00),
       ('Harry Potter and the Goblet of Fire', 2000, 20.00),
       ('Harry Potter and the Order of Phoenix', 2003, 20.00),
       ('Harry Potter and the Half-Blood Prince', 2005, 20.00),
       ('Harry Potter and the Deathly Hallows', 2007, 20.00),
       ('The Shining', 1977, 10.00),
       ('Salem’s Lot', 1975, 9.00),
       ('The Adventures of Tom Sawyer', 1876, 5.00);

-- delete some books what I dont want to see in my bookstore

DELETE FROM book WHERE title IN('Harry Potter and the Goblet of Fire',
                                'Harry Potter and the Deathly Hallows');


INSERT INTO book_author (book_id, author_id)
VALUES ((SELECT book_id FROM book WHERE title = '1984'),
        (SELECT author_id FROM author WHERE first_name = 'George' and last_name = 'Orwell'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Animals Farm'),
 (SELECT author_id FROM author WHERE first_name = 'George' and last_name = 'Orwell'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'The Old Man and the Sea'),
 (SELECT author_id FROM author WHERE first_name = 'Ernest' and last_name = 'Hemingway'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Harry Potter and the Philosopher Stone'),
 (SELECT author_id FROM author WHERE first_name = 'J.K.' and last_name = 'Rowling'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Harry Potter and the Chamber of Secrets'),
 (SELECT author_id FROM author WHERE first_name = 'J.K.' and last_name = 'Rowling'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Harry Potter and the Prisoner of Azkaban'),
 (SELECT author_id FROM author WHERE first_name = 'J.K.' and last_name = 'Rowling'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Harry Potter and the Order of Phoenix'),
 (SELECT author_id FROM author WHERE first_name = 'J.K.' and last_name = 'Rowling'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Harry Potter and the Half-Blood Prince'),
 (SELECT author_id FROM author WHERE first_name = 'J.K.' and last_name = 'Rowling'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'The Shining'),
 (SELECT author_id FROM author WHERE first_name = 'Stephen' and last_name = 'King'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'Salem’s Lot'),
 (SELECT author_id FROM author WHERE first_name = 'Stephen' and last_name = 'King'));

INSERT INTO book_author (book_id, author_id) VALUES
((SELECT book_id FROM book WHERE title = 'The Adventures of Tom Sawyer'),
 (SELECT author_id FROM author WHERE first_name = 'Mark' and last_name = 'Twain'));

-- add book which have 2 authors

INSERT INTO book (title, publication_year, price)
VALUES ('The Great Gatsby', 1925, 25.00);

INSERT INTO author (first_name, last_name)
VALUES ('F. Scott', 'Fitzgerald');

INSERT INTO book_author (book_id, author_id)
VALUES ((SELECT book_id FROM book WHERE title = 'The Great Gatsby'),
        (SELECT author_id FROM author WHERE first_name = 'F. Scott' and last_name = 'Fitzgerald')),

       ((SELECT book_id FROM book WHERE title = 'The Great Gatsby'),
        (SELECT author_id FROM author WHERE first_name = 'Ernest' and last_name = 'Hemingway'));


-- make some orders from our users

INSERT INTO "order" (user_id, order_date)
VALUES ((SELECT user_id FROM "user" WHERE username = 'Cristiano Ronaldo'), '2022-05-01'),
       ((SELECT user_id FROM "user" WHERE username = 'Lionel Messi'), '2022-06-01'),
       ((SELECT user_id FROM "user" WHERE username = 'Sergio Ramos'), '2022-07-01'),
       ((SELECT user_id FROM "user" WHERE username = 'Zinedine Zidane'), '2022-08-01'),
       ((SELECT user_id FROM "user" WHERE username = 'Cristiano Ronaldo'), '2022-09-01');


INSERT INTO order_book (order_id, book_id)
VALUES ((SELECT order_id FROM "order" WHERE user_id = (SELECT user_id FROM "user" WHERE
        username = 'Cristiano Ronaldo') AND order_date = '2022-05-01'),
        (SELECT book_id FROM book WHERE title = 'The Adventures of Tom Sawyer'));

INSERT INTO order_book (order_id, book_id) VALUES
((SELECT order_id FROM "order" WHERE user_id = (SELECT user_id FROM "user" WHERE
        username = 'Cristiano Ronaldo') AND order_date = '2022-05-01'),
 (SELECT book_id FROM book WHERE title = 'The Old Man and the Sea')),


((SELECT order_id FROM "order" WHERE user_id = (SELECT user_id FROM "user" WHERE
        username = 'Lionel Messi')),
 (SELECT book_id FROM book WHERE title = 'The Great Gatsby')),

((SELECT order_id FROM "order" WHERE user_id = (SELECT user_id FROM "user" WHERE
        username = 'Cristiano Ronaldo') AND order_date = '2022-09-01'),
 (SELECT book_id FROM book WHERE title = 'Harry Potter and the Philosopher Stone')),

((SELECT order_id FROM "order" WHERE user_id = (SELECT user_id FROM "user" WHERE
        username = 'Sergio Ramos')),
 (SELECT book_id FROM book WHERE title = 'Harry Potter and the Chamber of Secrets')),

((SELECT order_id FROM "order" WHERE user_id = (SELECT user_id FROM "user" WHERE
        username = 'Zinedine Zidane')),
 (SELECT book_id FROM book WHERE title = 'Animals Farm'));