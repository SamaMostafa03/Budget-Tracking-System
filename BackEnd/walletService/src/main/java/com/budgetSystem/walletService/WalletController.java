package com.budgetSystem.walletService;

import com.budgetSystem.walletService.dto.CreateWalletRequest;
import com.budgetSystem.walletService.dto.UpdateWalletRequest;
import com.budgetSystem.walletService.dto.WalletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/wallet")
public class WalletController {

    private final WalletService walletService;


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

