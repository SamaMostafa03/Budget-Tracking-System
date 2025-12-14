package com.budgetSystem.walletService.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wallets")
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Wallet name cannot be null")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Wallet type cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private WalletType type;

    @NotNull(message = "Wallet currency cannot be null")
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @NotNull(message = "Balance cannot be null")
    @Column(nullable = false)
    @PositiveOrZero(message = "balance cannot be negative or empty")
    private BigDecimal balance;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @NotNull(message = "ClientId cannot be null")
    @Column(nullable = false)
    @Positive(message = "ClientId cannot be negative")
    private Integer clientId;
}
