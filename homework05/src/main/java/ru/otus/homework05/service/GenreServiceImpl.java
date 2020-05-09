package ru.otus.homework05.service;

import org.springframework.stereotype.Service;
import ru.otus.homework05.dao.GenreDao;
import ru.otus.homework05.model.Genre;

@Service
public class GenreServiceImpl implements GenreService {
    final private GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public Genre getById(long id) {
        return genreDao.getById(id);
    }

    @Override
    public Genre getGenre(String genreName) {
        if (!checkGenreInBase(genreName)) genreDao.insert(new Genre(genreName));
        return genreDao.getByName(genreName);
    }

    private boolean checkGenreInBase(String genreName) {
        return genreDao.checkByName(genreName);
    }
}
