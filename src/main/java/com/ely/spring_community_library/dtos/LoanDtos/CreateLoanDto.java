package com.ely.spring_community_library.dtos.LoanDtos;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateLoanDto(

        @NotNull(message = "O ID do usuário é obrigatório.")
        Long userId,
        @NotNull(message = "O ID do livro é obrigatório.")
        Long bookId,
        @NotNull(message = "A data de locação é obrigatória.")
        LocalDate loanDate,
        LocalDate returnDate,
        @NotNull(message = "Se o livro foi retornado ou não é obrigatório.")
        Boolean returned
) {
}
