package com.BudgetTracking.UserService.Controller;
 
import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.Service.AdminService;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@ResponseBody
public class AdminController {

    @Autowired
    private AdminService adminService;
   
    @GetMapping("/getUsers")
    public ResponseEntity<?> getUserById() {
        return ResponseEntity.ok(new SuccessResponse(adminService.getAllUsers()));
    }
 
    @DeleteMapping("/deleteUsers/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer userId) {
        List<User> users = adminService.deleteUser(userId);
        SuccessResponse successResponse = new SuccessResponse();
        if (users.size() == 0)successResponse.setData("");
        else successResponse.setData(users);
        return ResponseEntity.ok(successResponse);
    }

    @PostMapping("/{clientId}/currency")
    public ResponseEntity<?> addCurrencyType(@PathVariable Integer clientId, @RequestParam String currencyType) {

        adminService.AddCurrencyType(clientId, currencyType);
        return ResponseEntity.ok(new SuccessResponse());
    }

    @GetMapping("/getTotalUsers")
    public ResponseEntity<?> getTotalNumberOfUsers() {
        return ResponseEntity.ok(new SuccessResponse(adminService.getTotalNumberOFUsers()));
    }
    
    @GetMapping("/getTodayUsers")
    public ResponseEntity<?> getTotalNumberOfUsersLoggedInToday() {
        return ResponseEntity.ok(new SuccessResponse(adminService.getTotalNumberOfUsersLoggedInToday()));
    }
    
    @GetMapping("/getNewUsers")
    public ResponseEntity<?> getTotalNumberOfUsersRegisteredToday() {
        return ResponseEntity.ok(new SuccessResponse(adminService.getTotalNumberOfUsersRegisteredToday()));
    }
    
    @GetMapping("/getUser/{clientId}")
    public ResponseEntity<?>  searchByClientId(@PathVariable Integer clientId) {
        return ResponseEntity.ok(new SuccessResponse(adminService.searchByID(clientId)));
    }

}
