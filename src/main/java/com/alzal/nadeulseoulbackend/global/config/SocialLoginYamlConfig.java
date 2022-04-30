package com.alzal.nadeulseoulbackend.global.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix="spring.security.oauth2.client.registration.google")
@Getter
@Setter
public class SocialLoginYamlConfig {
    private String clientId;
    private String scope;
}
