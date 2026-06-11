package vstu.practice.book_catalog.dto.request;

public record BookRequestDTO(
        String title,
        String isbn,
        String description,
        String genre,
        Long authorId
) {}