DROP TABLE IF EXISTS books;
CREATE TABLE books (
    id      BIGINT PRIMARY KEY  AUTO_INCREMENT,
    title   VARCHAR(255),
    genre_id BIGINT,
    author_id BIGINT
);

DROP TABLE IF EXISTS authors;
CREATE TABLE authors (
    id   BIGINT PRIMARY KEY  AUTO_INCREMENT,
    name  VARCHAR(255) UNIQUE
);

DROP TABLE IF EXISTS genres;
CREATE TABLE genres (
    id   BIGINT PRIMARY KEY  AUTO_INCREMENT,
    name  VARCHAR(255) UNIQUE
);

ALTER TABLE books
    ADD CONSTRAINT fk_bookGenre FOREIGN KEY (genre_id) REFERENCES genres(id);

ALTER TABLE books
    ADD CONSTRAINT fk_bookAuthor FOREIGN KEY (author_id) REFERENCES authors(id);
