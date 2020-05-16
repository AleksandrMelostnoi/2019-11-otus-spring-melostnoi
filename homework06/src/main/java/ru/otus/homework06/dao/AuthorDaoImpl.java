package ru.otus.homework06.dao;

import org.springframework.stereotype.Repository;
import ru.otus.homework06.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author insert(Author author) {
        if (author.getId() == null) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author getById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public Optional<Author> getByName(String name) {
        try {
            TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return  Optional.empty();
        }
    }

}
