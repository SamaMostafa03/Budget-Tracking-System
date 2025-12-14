package com.budgetSystem.walletService.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateWalletRequest {
    @NotNull(message = "Wallet ID is required")
    private Integer id;

    private String name;

    private String type;

    private String currency;

    @PositiveOrZero(message = "balance cannot be negative or empty")
    private BigDecimal balance;

}
