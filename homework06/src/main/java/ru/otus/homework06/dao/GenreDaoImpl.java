package ru.otus.homework06.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework06.entity.Genre;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Transactional
@Repository
public class GenreDaoImpl implements GenreDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Genre insert(Genre genre) {
        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Optional<Genre> getById(long id) {
        return Optional.ofNullable(em.find(Genre.class, id));
    }

    @Override
    public boolean checkByName(String name) {
        return getByName(name).isPresent();
    }

    @Override
    public Optional<Genre> getByName(String name) {
        try {
            TypedQuery<Genre> query = em.createQuery("select g from Genre g where g.name = :name", Genre.class);
            query.setParameter("name", name);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
