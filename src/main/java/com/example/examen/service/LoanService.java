package com.example.examen.service;

import com.example.examen.exception.LoanCannotBeCancelledException;
import com.example.examen.exception.LoanNotFoundException;
import com.example.examen.model.Loan;
import com.example.examen.model.Status;
import com.example.examen.model.Type;
import com.example.examen.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public Loan saveLoan(Loan loan){
        return loanRepository.save(loan);
    }

    public Optional<Loan> findById(Long id){
        return loanRepository.findById(id);
    }

    public void cancelLoan(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);
        if(!loanOptional.isPresent()) {
            throw new LoanNotFoundException("The payment does not exist");
        }

        Loan loan = loanOptional.get();
        if(loan.getType() == Type.AUTO && loan.getAmount() > 200000) {
            throw new LoanCannotBeCancelledException(
                    "The loan cannot be cancelled, as its value exceeds the admissible limit for AUTO loans");
        }

        loan.setStatus(Status.CANCELLED);
        loanRepository.save(loan);
    }
}
