package com.alibou.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionFilterRequest {
    private LocalDate startDate;
    private LocalDate endDate;
    private List<String> walletTypes;
}
