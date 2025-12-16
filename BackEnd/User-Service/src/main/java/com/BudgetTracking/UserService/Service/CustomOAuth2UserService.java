package com.BudgetTracking.UserService.Service;

import com.BudgetTracking.UserService.Model.AuthProvider;
import com.BudgetTracking.UserService.Model.Role;
import com.BudgetTracking.UserService.Model.User;
import com.BudgetTracking.UserService.Repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepo userRepository;

    public CustomOAuth2UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        userRepository.findByEmail(email).orElseGet( () ->{
            User user = User.builder()
                    .username(name)
                    .email(email)
                    .provider(AuthProvider.GOOGLE)
                    .role(Role.USER)
                    .build();
            return userRepository.save(user);
        });

        return oAuth2User;
    }
}
