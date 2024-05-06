package com.BudgetTracking.UserService.Service;

import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.exceptions.RecordNotFoundExecption;
import com.BudgetTracking.UserService.repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

  @Autowired
  private UserRepository userRepository;

  public List<User> getAllUsers() {
    List<User> users = userRepository.findAll();
    List<User> filteredUsers = new ArrayList<>();
    for (User user : users) {
      if ("User".equals(user.getRole().toString())) {
        filteredUsers.add(user);
      }
    }
    if(filteredUsers.size()==0)throw new RecordNotFoundExecption("No clients yet");
    return filteredUsers;
  }

  public void deleteUser(Integer userId)
  {
      searchByID(userId);
      userRepository.deleteById(userId);
  }

  public int getTotalNumberOFUsers() {
    List<User> users = userRepository.findAll();
    int totalUsers = 0;
    for (User user : users) {
      if ("User".equals(user.getRole().toString())) {
        totalUsers++;
      }
    }
    return totalUsers;

  }

  public int getTotalNumberOfUsersLoggedInToday() {
    LocalDate today = LocalDate.now();
    List<User> users = userRepository.findAll();
    int totalUsersLoggedInToday = 0;

    for (User user : users) {
      if ("User".equals(user.getRole().toString()) && user.getLoginDate() != null
          && user.getLoginDate().equals(today)) {
        totalUsersLoggedInToday++;
      }
    }

    return totalUsersLoggedInToday;
  }

  public int getTotalNumberOfUsersRegisteredToday() {
    LocalDate today = LocalDate.now();
    List<User> users = userRepository.findAll();
    int totalUsersRegisteredToday = 0;

    for (User user : users) {
      if ("User".equals(user.getRole().toString()) && user.getDate() != null
          && user.getDate().equals(today)) {
        totalUsersRegisteredToday++;
      }
    }

    return totalUsersRegisteredToday;
  }

  public User searchByID(Integer clientId) {
    Optional<User> user = userRepository.findById(clientId);
    if (user.isPresent()) {
      return user.get();
    }
    throw new RecordNotFoundExecption("Client id not found");
  }
}