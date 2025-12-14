package com.budgetSystem.walletService;

import com.budgetSystem.walletService.dto.CreateWalletRequest;
import com.budgetSystem.walletService.dto.UpdateWalletRequest;
import com.budgetSystem.walletService.dto.WalletResponse;
import com.budgetSystem.walletService.model.Currency;
import com.budgetSystem.walletService.model.Wallet;
import com.budgetSystem.walletService.model.WalletType;

public class WalletMapper {
    public static Wallet convertToEntity(CreateWalletRequest dto) {
        return Wallet.builder()
                .name(dto.getName())
                .type(WalletType.valueOf(dto.getType().toUpperCase()))
                .currency(Currency.valueOf(dto.getCurrency().toUpperCase()))
                .balance(dto.getBalance())
                .clientId(dto.getClientId())
                .build();
    }

    public static void updateEntity(UpdateWalletRequest dto, Wallet wallet) {
        if (dto.getName() != null) wallet.setName(dto.getName());
        if (dto.getType() != null)
            wallet.setType(WalletType.valueOf(dto.getType().toUpperCase()));
        if (dto.getCurrency() != null)
            wallet.setCurrency(Currency.valueOf(dto.getCurrency().toUpperCase()));
        if (dto.getBalance() != null) wallet.setBalance(dto.getBalance());
    }

    public static WalletResponse toResponce(Wallet wallet){
        return WalletResponse.builder()
                .id(wallet.getId())
                .clientId(wallet.getClientId())
                .createdAt(wallet.getCreatedAt())
                .updatedAt(wallet.getUpdatedAt())
                .name(wallet.getName())
                .type(wallet.getType().toString().toLowerCase())
                .balance(wallet.getBalance())
                .currency(wallet.getCurrency().toString().toLowerCase())
                .build();
    }
}
