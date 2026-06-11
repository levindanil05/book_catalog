package vstu.practice.book_catalog.dto.request;

import java.time.LocalDate;

public record AuthorRequestDTO(
        String fullName,
        LocalDate birthDate
) {}