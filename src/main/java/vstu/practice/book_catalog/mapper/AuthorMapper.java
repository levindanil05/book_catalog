package vstu.practice.book_catalog.mapper;

import vstu.practice.book_catalog.dto.request.AuthorRequestDTO;
import vstu.practice.book_catalog.dto.response.AuthorResponseDTO;
import vstu.practice.book_catalog.entity.Author;

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
    public static AuthorResponseDTO toResponse(Author author) {
        if (author == null) return null;

        return new AuthorResponseDTO(
                author.getId(),
                author.getFullName(),
                author.getBirthDate(),
                author.getBooks() != null
                        ? author.getBooks().stream().map(BookMapper::toResponse).toList()
                        : null
        );
    }
}