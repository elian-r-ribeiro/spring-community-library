package com.ely.spring_community_library.dtos.LoanDtos;

import java.time.LocalDate;

public record LoanDto(
        Long id,
        Long userId,
        Long bookId,
        LocalDate loanDate,
        LocalDate returnDate,
        Boolean returned
) {
}
