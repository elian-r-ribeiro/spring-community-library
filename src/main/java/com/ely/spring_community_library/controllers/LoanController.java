package com.ely.spring_community_library.controllers;

import com.ely.spring_community_library.dtos.LoanDtos.CreateLoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.LoanDto;
import com.ely.spring_community_library.entities.Loan;
import com.ely.spring_community_library.services.LoanService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    private ResponseEntity<Loan> createLoan(@RequestBody @Valid CreateLoanDto createLoanDto) {

        return loanService.createLoan(createLoanDto);
    }

    @GetMapping
    private ResponseEntity<List<LoanDto>> getAllLoans() {

        return loanService.getAllLoans();
    }
}
