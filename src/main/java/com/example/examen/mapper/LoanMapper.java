package com.example.examen.mapper;

import com.example.examen.dto.LoanDto;
import com.example.examen.model.Loan;
import org.springframework.stereotype.Component;

@Component
public class LoanMapper {
    public Loan loanDtoToLoan(LoanDto loanDto){
        return new Loan(loanDto.getType(), loanDto.getCustomer(),
                loanDto.getAmount(), loanDto.getStatus());
    }
}
