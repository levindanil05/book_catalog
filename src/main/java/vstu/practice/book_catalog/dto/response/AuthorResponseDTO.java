package vstu.practice.book_catalog.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record AuthorResponseDTO(
        Long id,
        String fullName,
        LocalDate birthDate,
        List<BookResponseDTO> books
) {}