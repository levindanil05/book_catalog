package vstu.practice.book_catalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vstu.practice.book_catalog.entity.Author;
import vstu.practice.book_catalog.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // Получить всех авторов
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // Получить автора по ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // Создать нового автора
    public Author createAuthor(Author author) {
        if (author.getFullName() == null || author.getFullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        return authorRepository.save(author);
    }

    // Удалить автора по ID
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}