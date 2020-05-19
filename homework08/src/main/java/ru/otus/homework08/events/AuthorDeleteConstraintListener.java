package ru.otus.homework08.events;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;
import ru.otus.homework08.Exception.DeleteAuthorConstraintException;
import ru.otus.homework08.entity.Author;
import ru.otus.homework08.repository.AuthorRepository;
import ru.otus.homework08.service.BookService;

import java.util.List;

import static java.lang.String.format;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

@Component
@RequiredArgsConstructor
public class AuthorDeleteConstraintListener extends AbstractMongoEventListener<Author> {

    private static final String AUTHORS_COLLECTION_NAME = "authors";

    private final AuthorRepository authorRepository;
    private final BookService bookService;

    @SneakyThrows
    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Author> event) {
        super.onBeforeDelete(event);
        String collectionName = event.getCollectionName();
        if (AUTHORS_COLLECTION_NAME.equalsIgnoreCase(collectionName)) {
            String authorId = String.valueOf(event.getSource().get("_id"));
            Author author = authorRepository.findById(authorId).orElseThrow(() -> new IllegalStateException(format("Author not found with id=%s", authorId)));
            List<String> books = bookService.getBooksByAuthorName(author.getName());
            if (isNotEmpty(books)) {
                throw new DeleteAuthorConstraintException("You cannot delete the author, as there is a connection with the book(s)! " + books.toString());
            }
        }
    }

}
