package com.budgetSystem.walletService;

import com.budgetSystem.walletService.dto.CreateWalletRequest;
import com.sama.wallet.UpdateWalletRequest;
import com.sama.wallet.WalletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;

    @GetMapping("/health")
    public String test() throws InterruptedException {
        Thread.sleep(200);
        return "OK";
    }
    @PostMapping("/create")
    public ResponseEntity<WalletResponse> saveWallet(@Valid @RequestBody CreateWalletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(walletService.createWallet(request));
    }

    @PutMapping("/update")
    public ResponseEntity<WalletResponse> updateWallet(@Valid @RequestBody UpdateWalletRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(walletService.updateWallet(request));
    }

    @GetMapping("client/{clientId}")
    public ResponseEntity<List<WalletResponse>> findAllWalletsByClient(@Valid @PathVariable Integer clientId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(walletService.findAllWalletsByClient(clientId));
    }

    @GetMapping("/{walletId}")
    public ResponseEntity<WalletResponse> findWallet(@Valid @PathVariable Integer walletId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(walletService.findWallet(walletId));
    }

    @DeleteMapping("/delete/{walletId}")
    public ResponseEntity<Void> deleteWallet(@Valid @PathVariable Integer walletId){
        walletService.deleteWallet(walletId);
        return ResponseEntity.noContent().build();
    }

}

