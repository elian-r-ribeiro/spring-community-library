package com.ely.spring_community_library.dtos.BookDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateBookDto(
        @NotBlank(message = "O título do livro é obrigatório.")
        @Size(min = 3, message = "O título do livro deve ter pelo menos 3 caracteres.")
        String title,
        @NotBlank(message = "O autor do livro é obrigatório.")
        @Size(min = 3, message = "O autor do livro deve ter pelo menos 3 caracteres.")
        String author,
        @NotBlank(message = "O ISBN do livro é obrigatório.")
        @Size(min = 13, message = "O ISBN do livro deve ter no mínimo 13 caracteres.")
        @Size(max = 13, message = "O ISBN do livro deve ter no máximo 13 caracteres.")
        String isbn,
        Integer availableCopies,
        Integer totalCopies
) {
}
