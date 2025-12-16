package com.BudgetTracking.UserService.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = "email")})
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Username is mandatory")
    @Column(nullable = false)
    private String username;

    @Email
    @NotBlank(message = "Email is mandatory")
    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private LocalDate registerDate;

    private LocalDate lastLoginDate;

    @PrePersist
    protected void onCreate() {
        this.registerDate = LocalDate.now();
        this.lastLoginDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastLoginDate = LocalDate.now();
    }
}
