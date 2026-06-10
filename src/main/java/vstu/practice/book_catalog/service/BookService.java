package vstu.practice.book_catalog.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vstu.practice.book_catalog.entity.Author;
import vstu.practice.book_catalog.entity.Book;
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
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Получить книгу по ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // Создать новую книгу
    public Book createBook(Book book) {
        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Book must have an author");
        }

        // Проверяем, что автор существует в БД
        Author author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + book.getAuthor().getId()));

        book.setAuthor(author);
        return bookRepository.save(book);
    }

    // Обновить книгу
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setDescription(bookDetails.getDescription());
        book.setGenre(bookDetails.getGenre());

        // Если передали нового автора — проверяем его существование
        if (bookDetails.getAuthor() != null && bookDetails.getAuthor().getId() != null) {
            Author author = authorRepository.findById(bookDetails.getAuthor().getId())
                    .orElseThrow(() -> new RuntimeException("Author not found with id: " + bookDetails.getAuthor().getId()));
            book.setAuthor(author);
        }

        return bookRepository.save(book);
    }

    // Удалить книгу
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new RuntimeException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}