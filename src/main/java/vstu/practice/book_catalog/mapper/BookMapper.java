package vstu.practice.book_catalog.mapper;

import vstu.practice.book_catalog.dto.request.BookRequestDTO;
import vstu.practice.book_catalog.dto.response.AuthorResponseDTO;
import vstu.practice.book_catalog.dto.response.BookResponseDTO;
import vstu.practice.book_catalog.entity.Book;

public class BookMapper {

    // Конвертация DTO в Entity
    public static Book toEntity(BookRequestDTO dto) {
        if (dto == null) return null;

        Book book = new Book();
        book.setTitle(dto.title());
        book.setIsbn(dto.isbn());
        book.setDescription(dto.description());
        book.setGenre(dto.genre());
        return book;
    }

    // Конвертация Entity в DTO
    public static BookResponseDTO toResponse(Book book) {
        if (book == null) return null;

        AuthorResponseDTO authorDto = null;
        if (book.getAuthor() != null) {
            authorDto = new AuthorResponseDTO(
                    book.getAuthor().getId(),
                    book.getAuthor().getFullName(),
                    book.getAuthor().getBirthDate(),
                    null
            );
        }

        return new BookResponseDTO(
                book.getId(),
                book.getTitle(),
                book.getIsbn(),
                book.getDescription(),
                book.getGenre(),
                authorDto
        );
    }
}