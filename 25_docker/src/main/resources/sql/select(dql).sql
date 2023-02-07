SELECT * FROM book;
SELECT * FROM "user";

SELECT username, email FROM "user";
SELECT title, publication_year, price FROM book;

SELECT DISTINCT order_date FROM "order";
SELECT DISTINCT price FROM book;

-- selects with conditions
SELECT title, price FROM book WHERE price > 20;
SELECT title FROM book WHERE title LIKE '%Harry Potter%';
SELECT username FROM "user" WHERE role_id IS NOT NULL;

SELECT title, publication_year FROM Book WHERE publication_year BETWEEN 2000 AND 2010;
SELECT title, price FROM book WHERE price NOT BETWEEN 10 AND 20;

SELECT username, email FROM "user" WHERE role_id = (SELECT role_id FROM role
                                                    WHERE role_name = 'administrator') AND email LIKE '%@nix.com';

SELECT title, price FROM Book WHERE price > (SELECT AVG(price) FROM Book);

-- order by
SELECT first_name, last_name FROM author ORDER BY last_name DESC;
SELECT title, price FROM book ORDER BY publication_year;

-- using UNION
SELECT title FROM book WHERE price >= 15 AND price < 20
UNION
SELECT title FROM book WHERE price < 10;

--write INNER JOINs
SELECT b.title, b.price, (SELECT a.last_name FROM author a WHERE a.author_id = ba.author_id)
FROM book AS b
         INNER JOIN book_author ba on b.book_id = ba.book_id;

SELECT b.title, a.first_name, a.last_name
FROM book AS b
         INNER JOIN book_author ba on b.book_id = ba.book_id
         INNER JOIN author a on ba.author_id = a.author_id;

-- write LEFT and OUTER JOINs
SELECT o.order_id, o.order_date, b.title
FROM "order" AS o
         LEFT JOIN order_book AS ob ON o.order_id = ob.order_id
         LEFT JOIN book AS b ON ob.book_id = b.book_id;

SELECT u.username, o.order_date
FROM "order" AS o
         FULL JOIN "user" AS u ON u.user_id = o.user_id;

-- query with numeric func
SELECT avg(price) FROM book;
SELECT count(author_id) FROM author;
SELECT min(publication_year) FROM book;

--query with string func
SELECT replace(title, 'The', 'THE') FROM book;
SELECT concat(first_name, ' ', last_name) FROM author;
SELECT substring(title, 1, 5) FROM book;

--query using GROUP BY and HAVING
SELECT role_name, COUNT(user_id) FROM "user" AS u
                                          INNER JOIN Role ON u.role_id = role.role_id
GROUP BY role_name;

SELECT role_name, COUNT(user_id) FROM "user" u
                                          INNER JOIN Role ON u.role_id = role.role_id
GROUP BY role_name
HAVING COUNT(user_id) > 2;
