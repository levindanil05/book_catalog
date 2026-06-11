package vstu.practice.book_catalog.dto.response;

public record BookResponseDTO(
        Long id,
        String title,
        String isbn,
        String description,
        String genre,
        AuthorResponseDTO author
) {}