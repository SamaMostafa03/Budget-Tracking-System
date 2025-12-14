package com.budgetSystem.walletService;

import com.budgetSystem.walletService.dto.CreateWalletRequest;
import com.budgetSystem.walletService.dto.UpdateWalletRequest;
import com.budgetSystem.walletService.dto.WalletResponse;
import com.budgetSystem.walletService.exceptions.RecordNotFoundExecption;
import com.budgetSystem.walletService.model.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletResponse createWallet(CreateWalletRequest dto){
        Wallet wallet = WalletMapper.convertToEntity(dto);
        walletRepository.save(wallet);
        return WalletMapper.toResponce(wallet);
    }

    public WalletResponse updateWallet(UpdateWalletRequest dto){
        Wallet wallet = walletRepository.findById(dto.getId())
                .orElseThrow( ()->new RecordNotFoundExecption("Wallet not found"));
        WalletMapper.updateEntity(dto, wallet);
        walletRepository.save(wallet);
        return WalletMapper.toResponce(wallet);
    }

    public void deleteWallet(int walletId){
        findWallet(walletId);
        walletRepository.deleteById(walletId);
    }

    public WalletResponse findWallet(int walletId){
        Wallet wallet = walletRepository.findById(walletId)
                .orElseThrow( ()->new RecordNotFoundExecption("Wallet not found"));
        return WalletMapper.toResponce(wallet);
    }

    public List<WalletResponse> findAllWalletsByClient(Integer clientId){
        List<Wallet> wallets = walletRepository.findAllByClientId(clientId);
        if(wallets.isEmpty())throw new RecordNotFoundExecption("No wallets created yet");
        return wallets.stream()
                .map(WalletMapper::toResponce)
                .toList();
    }

}
