package ru.otus.homework08.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.homework08.entity.Genre;
import ru.otus.homework08.repository.GenreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
    final private GenreRepository genreRepository;

    @Override
    public Optional<Genre> findByName(String genreName) {
        return genreRepository.findByName(genreName);
    }

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    @Transactional
    public void saveGenre(String genreName) {
        genreRepository.save(new Genre(genreName));
    }

    @Override
    @Transactional
    public void deleteById(String id) {
        genreRepository.deleteById(id);
    }

}
