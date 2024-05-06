package com.budgetSystem.transaction;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;
import jakarta.persistence.ElementCollection;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterRequestDTO {

    private LocalDate startDate;

    private LocalDate endDate;

    private List<String> walletTypes;
}
