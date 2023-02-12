package com.example.examen.dto;

import com.example.examen.model.Loan;
import com.example.examen.model.Status;
import com.example.examen.model.Type;
import jakarta.validation.constraints.*;
import org.springframework.stereotype.Component;

@Component
public class LoanDto {
    @NotNull
    private Type type;
    @NotBlank
    @Size(max = 200, message = "{validation.name.size.too_long}")
    private String customer;
    @NotNull
    @Positive
    private Double amount;
    @NotNull
    private Status status;

    public LoanDto(){}

    public LoanDto(Type type, String customer, Double amount, Status status) {
        this.type = type;
        this.customer = customer;
        this.amount = amount;
        this.status = status;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
