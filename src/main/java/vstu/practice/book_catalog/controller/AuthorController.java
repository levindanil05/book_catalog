package vstu.practice.book_catalog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vstu.practice.book_catalog.dto.request.AuthorRequestDTO;
import vstu.practice.book_catalog.dto.response.AuthorResponseDTO;
import vstu.practice.book_catalog.service.AuthorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    // Создать автора
    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createAuthor(@RequestBody AuthorRequestDTO dto) {
        AuthorResponseDTO createdAuthor = authorService.createAuthor(dto);
        return ResponseEntity.ok(createdAuthor);
    }

    // Получить всех авторов
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllAuthors() {
        List<AuthorResponseDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok(authors);
    }

    // Получить автора по ID (с книгами)
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        Optional<AuthorResponseDTO> author = authorService.getAuthorById(id);
        return author.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Удалить автора
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        try {
            authorService.deleteAuthor(id);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}