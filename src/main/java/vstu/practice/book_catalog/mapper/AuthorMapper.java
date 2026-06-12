package vstu.practice.book_catalog.mapper;

import vstu.practice.book_catalog.dto.request.AuthorRequestDTO;
import vstu.practice.book_catalog.dto.response.AuthorResponseDTO;
import vstu.practice.book_catalog.dto.response.BookResponseDTO;
import vstu.practice.book_catalog.entity.Author;

import java.util.List;

public class AuthorMapper {

    // Конвертация DTO в Entity
    public static Author toEntity(AuthorRequestDTO dto) {
        if (dto == null) return null;

        Author author = new Author();
        author.setFullName(dto.fullName());
        author.setBirthDate(dto.birthDate());
        return author;
    }

    // Конвертация Entity в DTO
    public static AuthorResponseDTO toResponse(Author author, boolean includeBooks) {
        if (author == null) return null;

        List<BookResponseDTO> books;

        if (includeBooks && author.getBooks() != null){
            books = author.getBooks().stream().map(BookMapper::toResponse).toList();
        } else{
            books = List.of();
        }

        return new AuthorResponseDTO(
                author.getId(),
                author.getFullName(),
                author.getBirthDate(),
                books
        );
    }

    public static AuthorResponseDTO toResponseWithoutBooks(Author author) {
        return toResponse(author, false);
    }

    public static AuthorResponseDTO toResponseWithBooks(Author author) {
        return toResponse(author, true);
    }
}