package com.budgetSystem.transaction.clients;

import com.sama.wallet.UpdateWalletRequest;
import com.sama.wallet.WalletResponse;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Configuration
@FeignClient(name = "WALLET-SERVICE",url = "${application.config.wallet-url}")
public interface WalletClient {

    @PutMapping("/update")
    public ResponseEntity<WalletResponse> updateWallet(@Valid @RequestBody UpdateWalletRequest request);

    @GetMapping("client/{clientId}")
    public ResponseEntity<List<WalletResponse>> findAllWalletsByClient(@Valid @PathVariable Integer clientId);

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponse> findWallet(@Valid @PathVariable Integer walletId);
}
