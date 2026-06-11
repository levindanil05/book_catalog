package vstu.practice.book_catalog.dto.response;

import java.time.LocalDate;
import java.util.List;

public record AuthorResponseDTO(
        Long id,
        String fullName,
        LocalDate birthDate,
        List<BookResponseDTO> books
) {}