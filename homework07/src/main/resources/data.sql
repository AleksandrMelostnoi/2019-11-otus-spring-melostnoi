INSERT INTO genres (id, name) VALUES (1, 'Сказка');
INSERT INTO genres (id, name) VALUES (2, 'Комедия');
INSERT INTO genres (id, name) VALUES (3, 'Детектив');
INSERT INTO genres (id, name) VALUES (4, 'Роман');
INSERT INTO genres (id, name) VALUES (5, 'Фантастика');

INSERT INTO authors (id, name) VALUES (1, 'Рэй Брэдбери');
INSERT INTO authors (id, name) VALUES (2, 'Шекспир');
INSERT INTO authors (id, name) VALUES (3, 'Толстой');
INSERT INTO authors (id, name) VALUES (4, 'Конан Дойл');
INSERT INTO authors (id, name) VALUES (5, 'Стивен Кинг');

INSERT INTO books (id, title) VALUES (1, 'Из праха восставшие');
INSERT INTO books (id, title) VALUES (2, 'Укрощение строптивой');
INSERT INTO books (id, title) VALUES (3, 'Война и мир');
INSERT INTO books (id, title) VALUES (4, 'Шерлок Холмс');
INSERT INTO books (id, title) VALUES (5, 'Институт');
INSERT INTO books (id, title) VALUES (6, 'Затерянный мир');

INSERT INTO books_authors (book_id, author_id) values (1, 1);
INSERT INTO books_authors (book_id, author_id) values (2, 2);
INSERT INTO books_authors (book_id, author_id) values (3, 3);
INSERT INTO books_authors (book_id, author_id) values (4, 4);
INSERT INTO books_authors (book_id, author_id) values (5, 5);
INSERT INTO books_authors (book_id, author_id) values (1, 5);
INSERT INTO books_authors (book_id, author_id) values (6, 4);

INSERT INTO books_genres (book_id, genre_id) values (1, 1);
INSERT INTO books_genres (book_id, genre_id) values (2, 2);
INSERT INTO books_genres (book_id, genre_id) values (3, 4);
INSERT INTO books_genres (book_id, genre_id) values (4, 3);
INSERT INTO books_genres (book_id, genre_id) values (5, 5);
INSERT INTO books_genres (book_id, genre_id) values (4, 4);
INSERT INTO books_genres (book_id, genre_id) values (6, 5);