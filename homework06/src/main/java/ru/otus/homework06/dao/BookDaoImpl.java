package ru.otus.homework06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.homework06.entity.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class BookDaoImpl implements BookDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book insert(Book book) {
        return em.merge(book);
    }

    @Override
    public Book getById(long id) {
        return Optional.ofNullable(em.find(Book.class, id)).get();
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
        em.remove(getById(id));
    }

}
