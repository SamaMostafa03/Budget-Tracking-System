package com.budgetSystem.walletService.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateWalletRequest {
    @NotNull(message = "Wallet name cannot be null")
    private String name;

    @NotNull(message = "Wallet type cannot be null")
    private String type;

    @NotNull(message = "Wallet currency cannot be null")
    private String currency;

    @NotNull(message = "Balance cannot be null")
    @PositiveOrZero(message = "balance cannot be negative or empty")
    private BigDecimal balance;

    @NotNull(message = "ClientId cannot be null")
    @Positive(message = "ClientId cannot be negative")
    private Integer clientId;
}
