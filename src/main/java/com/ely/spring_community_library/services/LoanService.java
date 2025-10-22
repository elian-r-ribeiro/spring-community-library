package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.LoanDtos.CreateLoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.LoanDto;
import com.ely.spring_community_library.entities.Book;
import com.ely.spring_community_library.entities.Loan;
import com.ely.spring_community_library.entities.User;
import com.ely.spring_community_library.repositories.BookRepository;
import com.ely.spring_community_library.repositories.LoanRepository;
import com.ely.spring_community_library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BookRepository bookRepository;

    public ResponseEntity<Loan> createLoan(CreateLoanDto createLoanDto) {

        Optional<User> userFromLoan = userRepository.findById(createLoanDto.userId());
        Optional<Book> bookFromLoan = bookRepository.findById(createLoanDto.bookId());

        if(userFromLoan.isPresent() && bookFromLoan.isPresent()) {

            Loan newLoan = new Loan(
                    null,
                    userFromLoan.get(),
                    bookFromLoan.get(),
                    createLoanDto.loanDate(),
                    createLoanDto.returnDate(),
                    createLoanDto.returned()
            );

            return ResponseEntity.ok(loanRepository.save(newLoan));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    public ResponseEntity<List<LoanDto>> getAllLoans() {

        List<Loan> loans = loanRepository.findAll();

        return ResponseEntity.ok(convertLoansToLoanDtos(loans));
    }

    private List<LoanDto> convertLoansToLoanDtos(List<Loan> loans) {

        return loans.stream()
                .map(loan -> new LoanDto(
                        loan.getId(),
                        loan.getUser().getName(),
                        loan.getBook().getTitle(),
                        loan.getLoanDate(),
                        loan.getReturnDate(),
                        loan.isReturned()
                )).toList();
    }
}
