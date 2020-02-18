package ru.otus.homework05.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.homework05.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final String queryTemplate = "select b.id as bookId, b.name as bookName, " +
            "b.author_id as authorId, b.genre_id as genreId " +
            "from library_books b ";

    private final NamedParameterJdbcOperations jdbc;
    private final AuthorRepository authorsRepository;
    private final GenreRepository genresRepository;

    @Autowired
    public BooksRepositoryImpl(NamedParameterJdbcOperations jdbc,
                               AuthorsRepository authorsRepository,
                               GenresRepository genresRepository) {
        this.jdbc = jdbc;
        this.authorsRepository = authorsRepository;
        this.genresRepository = genresRepository;
        this.mapper = new BookMapper(authorsRepository, genresRepository);
    }

    private final BookMapper mapper;

    @Override
    public List<Book> findAll() {
        return jdbc.getJdbcOperations().query(queryTemplate, mapper);
    }

    @Override
    public Book findById(long id) {
        return jdbc.getJdbcOperations()
                .queryForObject(queryTemplate + " where b.id = ?", new Object[] {id}, mapper);
    }

    @Override
    public List<Book> findByName(String name) {
        Map<String, String> params = Collections.singletonMap("name", name);
        return jdbc.query(queryTemplate + " where b.name = :name", params, mapper);
    }

    @Override
    public void insert(Book book) {
        List<Object> params = new ArrayList<>();
        params.add(book.getId());
        params.add(book.getName());
        String fields = "id, name";
        String values = "?,?";

        if (book.getAuthor() != null) {
            fields = fields + ", author_id";
            values = values + ",?";
            params.add(book.getAuthor().getId());
        }

        if (book.getGenre() != null) {
            fields = fields + ", genre_id";
            values = values + ",?";
            params.add(book.getGenre().getId());
        }

        jdbc.getJdbcOperations()
                .update("insert into library_books (" + fields + ") values (" + values + ")", params.toArray());
    }

    @Override
    public Book insert(String name, Author author, Genre genre) {
        long nextId = getNextId();
        Book book = new Book(nextId, name, author, genre);
        insert(book);
        return book;
    }

    @Override
    public void update(Book book) {
        String names = "name = ?";
        List<Object> values = new ArrayList<>();
        values.add(book.getName());

        if (book.getAuthor() == null) {
            names += ", author_id = null";
        } else {
            names += ", author_id = ?";
            values.add(book.getAuthor().getId());
        }

        if (book.getGenre() == null) {
            names += ", genre_id = null";
        } else {
            names += ", genre_id = ?";
            values.add(book.getGenre().getId());
        }

        values.add(book.getId());
        jdbc.getJdbcOperations().update("update library_books set " + names + " where id = ?", values.toArray());
    }

    @Override
    public void deleteById(long id) {
        jdbc.getJdbcOperations().update("delete from library_books where id = ?", id);
    }

    @Override
    public int count() {
        return jdbc.getJdbcOperations().queryForObject("select count(*) from library_books", Integer.class);
    }

    @Override
    public List<Book> findByAuthorName(String authorName) {
        List<Author> authors = authorsRepository.findByName(authorName);
        if (!authors.isEmpty()) {
            List<Long> ids = authors.stream().map(Entity::getId).collect(Collectors.toList());
            Map<String, Object> params = Collections.singletonMap("authorIds", ids);
            return jdbc.query(queryTemplate + " where b.author_id in (:authorIds)", params, mapper);
        }
        return Collections.emptyList();
    }

    @Override
    public List<Book> findByGenre(String genreName) {
        Genre genre = genresRepository.findByName(genreName);
        if (genre != null) {
            Map<String, Object> params = Collections.singletonMap("genreId", genre.getId());
            return jdbc.query(queryTemplate + " where b.genre_id = :genreId", params, mapper);
        }
        return Collections.emptyList();
    }

    @Override
    public void insert(long id, String name, String authorName, String genreName) {
        List<Author> authors = authorsRepository.findByName(authorName);
        Genre genre = genresRepository.findByName(genreName);
        Book book = new Book(id, name, null, genre);

        if (!authors.isEmpty()) {
            book.setAuthor(authors.get(0));
        }
        insert(book);
    }

    /**
     * TODO поиск в единой транзации
     * @return
     */
    @Override
    public long getNextId() {
        if (count() == 0) {
            return 1;
        }

        return jdbc.getJdbcOperations().queryForObject("select max(id) from library_books", Long.class) + 1;
    }

    private static class BookMapper implements RowMapper<Book> {

        private final AuthorsRepository authorsRepository;
        private final GenresRepository genresRepository;

        public BookMapper(AuthorsRepository authorsRepository, GenresRepository genresRepository) {
            this.authorsRepository = authorsRepository;
            this.genresRepository = genresRepository;
        }

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Author author = null;
            Long authorId = resultSet.getLong("authorId");
            if (authorId != null) {
                author = authorsRepository.findById(authorId);
            }

            Genre genre = null;
            Long genreId = resultSet.getLong("genreId");
            if (genreId != null) {
                genre = genresRepository.findById(genreId);
            }

            long bookId = resultSet.getLong("bookId");
            String bookName = resultSet.getString("bookName");
            return new Book(bookId, bookName, author, genre);
        }
    }

}
