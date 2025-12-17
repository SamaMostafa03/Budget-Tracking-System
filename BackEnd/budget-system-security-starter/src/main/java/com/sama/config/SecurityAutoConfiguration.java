package com.sama.config;

import com.sama.config.JwtProperties;
import com.sama.jwt.JwtService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public JwtService jwtService(JwtProperties properties) {
        return new JwtService(properties);
    }
}
