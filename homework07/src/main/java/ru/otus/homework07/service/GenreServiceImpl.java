package ru.otus.homework07.service;

import org.springframework.stereotype.Service;
import ru.otus.homework07.repository.GenreRepository;
import ru.otus.homework07.entity.Genre;

@Service
public class GenreServiceImpl implements GenreService {
    final private GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre findById(long id) {
        return genreRepository.findById(id).get();
    }

    @Override
    public Genre findByName(String name) {
        if (!genreRepository.findByName(name).isPresent()) {
            genreRepository.save(new Genre(name));
        }
        return genreRepository.findByName(name).get();
    }

}
