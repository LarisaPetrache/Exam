package com.example.examen.controller;

import com.example.examen.dto.LoanDto;
import com.example.examen.exception.LoanCannotBeCancelledException;
import com.example.examen.exception.LoanNotFoundException;
import com.example.examen.mapper.LoanMapper;
import com.example.examen.model.Loan;
import com.example.examen.service.LoanService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final LoanMapper loanMapper;

    public LoanController(LoanService loanService, LoanMapper loanMapper) {
        this.loanService = loanService;
        this.loanMapper = loanMapper;
    }

    @PostMapping("/new")
    public ResponseEntity<Loan> saveLoan(@RequestBody @Valid LoanDto loanDto){
        Loan loan = loanService.saveLoan(loanMapper.loanDtoToLoan(loanDto));

        return ResponseEntity.created(URI.create("/loans/" + loan.getLoanId()))
                .body(loan);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelLoan(@PathVariable Long id) {
        try {
            loanService.cancelLoan(id);
            return new ResponseEntity<>("Loan cancelled successfully", HttpStatus.OK);
        } catch (LoanNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (LoanCannotBeCancelledException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
