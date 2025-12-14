package com.BudgetTracking.UserService.Service;

import com.BudgetTracking.UserService.Model.AuthenticationResponse;
import com.BudgetTracking.UserService.Model.Role;
import com.BudgetTracking.UserService.Model.Token;
import com.BudgetTracking.UserService.Model.User;

import com.sama.exceptions.RecordExistException;
import com.sama.exceptions.RecordNotFoundException;
import com.BudgetTracking.UserService.repository.TokenRepository;
import com.BudgetTracking.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@ComponentScan
public class AuthenticationService {
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    public AuthenticationService(UserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager, UserRepository userRepository,
            TokenRepository tokenRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    public AuthenticationResponse register(User request) {
        Optional<User> existUser=repository.findByEmail(request.getEmail());
        if(existUser.isPresent())
        {
            throw new RecordExistException("Email exist by other client");
        }
        User user=new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.User);
        user.setDate(LocalDate.now());
        user=repository.save(user);
        String jwt=jwtService.generateToken(user);

        saveUserToken(jwt, user);

        return new AuthenticationResponse(jwt);
    }



    public AuthenticationResponse authenticate(User request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user=repository.findByEmail(request.getEmail()).orElseThrow(()-> new RecordNotFoundExecption("User not found"));
        String token = jwtService.generateToken(user);
        user.setLoginDate(LocalDate.now());

       // Save the updated user entity back to the database
        repository.save(user);
        
        revokeAllTokenListByUser(user);


        saveUserToken(token,user);

        return new AuthenticationResponse(token);

    }

    private void revokeAllTokenListByUser(User user) {
        List<Token> validTokenListByUser = tokenRepository.findAllTokenByUser(user.getId());

        if(!validTokenListByUser.isEmpty()) {
            validTokenListByUser.forEach(t -> {
                t.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveUserToken(  String jwt,User user) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setUser(user);
        tokenRepository.save(token);
    }

}

