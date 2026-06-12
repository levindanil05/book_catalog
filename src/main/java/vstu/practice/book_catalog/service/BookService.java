package vstu.practice.book_catalog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vstu.practice.book_catalog.dto.request.BookRequestDTO;
import vstu.practice.book_catalog.dto.response.BookResponseDTO;
import vstu.practice.book_catalog.entity.Author;
import vstu.practice.book_catalog.entity.Book;
import vstu.practice.book_catalog.mapper.BookMapper;
import vstu.practice.book_catalog.repository.AuthorRepository;
import vstu.practice.book_catalog.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    // Получить все книги
    public List<BookResponseDTO> getAllBooks() {
        return bookRepository.findAllWithAuthor()
                .stream()
                .map(BookMapper::toResponse)
                .toList();
    }

    // Получить книгу по ID
    public Optional<BookResponseDTO> getBookById(Long id) {
        return bookRepository.findById(id).map(BookMapper::toResponse);
    }

    // Создать новую книгу
    public BookResponseDTO createBook(BookRequestDTO dto) {
        if (dto.authorId() == null) {
            throw new IllegalArgumentException("Book must have an author");
        }

        // Проверяем, что автор существует в БД
        Author author = authorRepository.findById(dto.authorId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + dto.authorId()));

        Book book = BookMapper.toEntity(dto);
        book.setAuthor(author);
        return BookMapper.toResponse(bookRepository.save(book));
    }

    // Обновить книгу
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setDescription(dto.description());
        book.setGenre(dto.genre());

        // Если передали нового автора — проверяем его существование
        if (dto.authorId() != null) {
            Author author = authorRepository.findById(dto.authorId())
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + dto.authorId()));
            book.setAuthor(author);
        }

        return BookMapper.toResponse(bookRepository.save(book));
    }

    // Удалить книгу
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}