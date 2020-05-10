package ru.otus.homework06.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework06.Exception.EmptyFieldException;
import ru.otus.homework06.entity.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book insert(Book book) throws EmptyFieldException {
        if (!checkEmptyField(book).isEmpty()){
            throw new EmptyFieldException(" Ошибка! У книги отсутствует поле " + checkEmptyField(book));
        }
        return em.merge(book);
    }

    @Override
    public Optional<Book> getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id));
    }

    @Override
    public long getCount() {
        return em.createQuery("select count(b) from Book b", Long.class).getSingleResult();
    }

    @Override
    public List<Book> getAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("author_genre_entity_graph");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph",entityGraph);
        return query.getResultList();
    }

    public void deleteById(long id) {
        Query query = em.createQuery("delete from Book b where b.id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
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
