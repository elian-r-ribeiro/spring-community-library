package com.ely.spring_community_library.dtos.LoanDtos;

import java.time.LocalDate;

public record LoanDto(
        Long id,
        String userName,
        String bookTitle,
        LocalDate loanDate,
        LocalDate returnDate,
        Boolean returned
) {
}
