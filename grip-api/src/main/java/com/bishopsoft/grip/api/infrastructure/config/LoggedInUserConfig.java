package com.bishopsoft.grip.api.infrastructure.config;

import com.bishopsoft.grip.api.infrastructure.security.LoggedInUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Configuration
public class LoggedInUserConfig {
    @Bean
    @RequestScope
    public LoggedInUser loggedInUser(HttpServletRequest request) {
        return LoggedInUser.builder()
                .id(UUID.fromString(request.getUserPrincipal().getName()))
                .build();
    }

}
