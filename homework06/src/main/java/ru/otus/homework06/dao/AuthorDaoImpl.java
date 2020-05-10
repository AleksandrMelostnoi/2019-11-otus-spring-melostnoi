package ru.otus.homework06.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework06.entity.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Transactional
@Repository
public class AuthorDaoImpl implements AuthorDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Author insert(Author author) {
        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Optional<Author> getById(long id) {
        return Optional.ofNullable(em.find(Author.class, id));
    }

    @Override
    public boolean checkByName(String name) {
        TypedQuery<Author> query = em.createQuery("select a from Author a where a.name = :name", Author.class);
        query.setParameter("name", name);
        return query.getResultList().size() != 0;
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
