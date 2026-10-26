package com.example.banksystem.account.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositDTO {
@NotNull(message = "Amount is required")
@DecimalMin(value = "0.01", message = "Deposits and Withdrawals must be greater than zero")
@Digits(integer = 10, fraction = 2, message = "Amount must have at most two decimal places")
private BigDecimal amount;
}
