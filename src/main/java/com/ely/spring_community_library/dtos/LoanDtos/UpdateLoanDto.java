package com.ely.spring_community_library.dtos.LoanDtos;

import java.time.LocalDate;

public record UpdateLoanDto(

        Long userId,
        Long bookId,
        LocalDate loanDate,
        LocalDate returnDate,
        Boolean returned
) {
}
