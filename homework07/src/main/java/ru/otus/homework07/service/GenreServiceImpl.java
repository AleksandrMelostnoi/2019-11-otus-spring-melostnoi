package ru.otus.homework07.service;

import org.springframework.stereotype.Service;
import ru.otus.homework07.entity.Genre;
import ru.otus.homework07.repository.GenreRepository;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    final private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findByName(String genreName) {
        if (!genreRepository.findByName(genreName).isPresent()) {
            genreRepository.save(new Genre(genreName));
        }
        return genreRepository.findByName(genreName).get();
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

}
