package vstu.practice.book_catalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vstu.practice.book_catalog.dto.request.AuthorRequestDTO;
import vstu.practice.book_catalog.dto.response.AuthorResponseDTO;
import vstu.practice.book_catalog.entity.Author;
import vstu.practice.book_catalog.mapper.AuthorMapper;
import vstu.practice.book_catalog.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    // Получить всех авторов
    public List<AuthorResponseDTO> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toResponseWithoutBooks)
                .toList();
    }

    // Получить автора по ID
    public Optional<AuthorResponseDTO> getAuthorById(Long id) {
        return authorRepository.findById(id).map(AuthorMapper::toResponseWithBooks);
    }

    // Создать нового автора
    public AuthorResponseDTO createAuthor(AuthorRequestDTO dto) {
        if (dto.fullName() == null || dto.fullName().trim().isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        Author author = AuthorMapper.toEntity(dto);
        return AuthorMapper.toResponseWithBooks(authorRepository.save(author));
    }

    // Удалить автора по ID
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}