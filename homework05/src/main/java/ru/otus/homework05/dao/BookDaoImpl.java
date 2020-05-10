package ru.otus.homework05.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.homework05.Exception.BookAlreadyExistsException;
import ru.otus.homework05.Exception.EmptyFieldException;
import ru.otus.homework05.model.Author;
import ru.otus.homework05.model.Book;
import ru.otus.homework05.model.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class BookDaoImpl implements BookDao {
    private final NamedParameterJdbcOperations jdbcOperations;

    @Autowired
    public BookDaoImpl(NamedParameterJdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public long insert(Book book) throws BookAlreadyExistsException, EmptyFieldException {
        if (checkExists(book)){
            throw new BookAlreadyExistsException(" Ошибка! Книга " + book.getTitle() + " уже находится в базе!");
        }
        if (!checkEmptyField(book).isEmpty()){
            throw new EmptyFieldException(" Ошибка! У книги отсутствует поле " + checkEmptyField(book));
        }
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("title", book.getTitle());
        params.addValue("genreId", book.getGenre().getId());
        params.addValue("authorId", book.getAuthor().getId());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update("insert into book (title, genreId, authorId) values(:title, :genreId, :authorId)", params, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).longValue();
    }

    @Override
    public Book getById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        return jdbcOperations.queryForObject("select b.id, b.title, b.genreId, b.authorId, a.name authorName, g.name genreName " +
                        "from (book b left join author a on b.authorId = a.id) " +
                        "left join genre g on b.genreId = g.id " +
                        "where b.id = :id",
                params, new BookMapper());
    }

    @Override
    public long getCount() {
        return jdbcOperations.queryForObject("select count(*) from book", new HashMap<>(1), Long.class);
    }

    @Override
    public List<Book> getAll() {
        return jdbcOperations.query(
                "select b.id, b.title, b.genreId, b.authorId, a.name authorName, g.name genreName " +
                       "from (book b left join author a on b.authorId = a.id) " +
                           "left join genre g on b.genreId = g.id",
                new BookMapper());
    }

    public void deleteById(long id) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("id", id);
        jdbcOperations.update("delete from book where id=:id", params);
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            String title = resultSet.getString("title");
            Book book = new Book(id, title);
            book.setAuthor(new Author(resultSet.getLong("authorId"), resultSet.getString("authorName")));
            book.setGenre(new Genre(resultSet.getLong("genreId"), resultSet.getString("genreName")));
            return book;
        }
    }

    private boolean checkExists(Book book) {
        final Map<String, Object> params = new HashMap<>(1);
        params.put("title", book.getTitle());
        params.put("genreId", book.getGenre().getId());
        params.put("authorId", book.getAuthor().getId());
        int count = jdbcOperations.queryForObject(
                "select count(*) from book where title = :title and genreId = :genreId and authorId = :authorId",
                params, Integer.class);
        return count > 0;
    }

    private String checkEmptyField(Book book) {
        if (book.getTitle().isEmpty()) {
            return "title";
        } else if (book.getAuthor().getName().isEmpty()) {
            return "author";
        } else if (book.getGenre().getName().isEmpty()) {
            return "genre";
        }
        return "";
    }

}
