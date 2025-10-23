package com.ely.spring_community_library.controllers;

import com.ely.spring_community_library.dtos.LoanDtos.CreateLoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.DeleteLoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.LoanDto;
import com.ely.spring_community_library.dtos.LoanDtos.UpdateLoanDto;
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

    @GetMapping("/{id}")
    private ResponseEntity<LoanDto> getLoanById(@PathVariable("id") Long id) {

        return loanService.getLoanById(id);
    }

    @PutMapping("/{id}")
    private ResponseEntity<LoanDto> updateLoanById(
            @PathVariable("id") Long id,
            @RequestBody @Valid UpdateLoanDto updateLoanDto
    ) {

        return loanService.updateLoanById(id, updateLoanDto);
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> deleteLoanById(
            @PathVariable("id") Long id,
            @RequestBody DeleteLoanDto deleteLoanDto
    ) {

        return loanService.deleteLoanById(id, deleteLoanDto);
    }
}
