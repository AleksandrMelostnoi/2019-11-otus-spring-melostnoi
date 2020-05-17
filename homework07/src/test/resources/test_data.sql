INSERT INTO genres (id, name) VALUES (1, 'Ужасы');

INSERT INTO authors (id, name) VALUES (1, 'Стивен Кинг');

INSERT INTO books (id, title) VALUES (1, 'Оно');

INSERT INTO books_authors (book_id, author_id) values (1, 1);

INSERT INTO books_genres (book_id, genre_id) values (1, 1);
