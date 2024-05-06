package com.BudgetTracking.UserService.Controller;
 
import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.Service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthenticationController {
    private final AuthenticationService authService;

    public AuthenticationController(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User request
            ){
        return ResponseEntity.ok(new SuccessResponse(authService.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody User request
    ){
        return  ResponseEntity.ok(new SuccessResponse(authService.authenticate(request)));
    }






 



}
