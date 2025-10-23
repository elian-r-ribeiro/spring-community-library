package com.ely.spring_community_library.services;

import com.ely.spring_community_library.dtos.LoanDtos.CreateLoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.LoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.UpdateLoanDto;
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

@SuppressWarnings("all")
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

    public ResponseEntity<LoanDto> getLoanById(Long id) {

        final Optional<Loan> loan = loanRepository.findById(id);

        if(loan.isPresent()) {

            return ResponseEntity.ok(convertLoanToLoanDto(loan.get()));
        } else {

            return ResponseEntity.notFound().build();
        }
    }

    private LoanDto convertLoanToLoanDto(Loan loan) {

        return new LoanDto(
                loan.getId(),
                loan.getUser().getName(),
                loan.getBook().getTitle(),
                loan.getLoanDate(),
                loan.getReturnDate(),
                loan.isReturned()
        );
    }

    public ResponseEntity<Loan> updateLoanById(Long id, UpdateLoanDto updateLoanDto) {

        final Optional<Loan> oldLoan = loanRepository.findById(id);

        if(oldLoan.isPresent()) {

            ResponseEntity<Loan> response = checkIfUserAndBookArePresent(
                    updateLoanDto,
                    oldLoan.get());

            if(!response.getStatusCode().is2xxSuccessful()) {
                return ResponseEntity.status(response.getStatusCode()).build();
            }

            Loan updatedLoan = response.getBody();

            return ResponseEntity.ok(mergeLoanChanges(updateLoanDto, updatedLoan));

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private Loan mergeLoanChanges(UpdateLoanDto updateLoanDto, Loan loanToUpdate) {

        if(updateLoanDto.loanDate() != null) {

            loanToUpdate.setLoanDate(updateLoanDto.loanDate());
        }
        if(updateLoanDto.returnDate() != null) {

            loanToUpdate.setReturnDate(updateLoanDto.returnDate());
        }
        if(updateLoanDto.returned() != null) {

            loanToUpdate.setReturned(updateLoanDto.returned());
        }

        return loanToUpdate;
    }

    private ResponseEntity<Loan> checkIfUserAndBookArePresent(UpdateLoanDto updateLoanDto, Loan loan) {

        if(updateLoanDto.userId() != null) {
            Optional<User> user = userRepository.findById(updateLoanDto.userId());

            if(!user.isPresent()) {

                return ResponseEntity.notFound().build();
            } else {

                loan.setUser(user.get());
            }
        }

        if(updateLoanDto.bookId() != null) {

            Optional<Book> book = bookRepository.findById(updateLoanDto.bookId());

            if(!book.isPresent()) {

                return ResponseEntity.notFound().build();
            } else {

                loan.setBook(book.get());
            }
        }

        return ResponseEntity.ok(loan);
    }
}
