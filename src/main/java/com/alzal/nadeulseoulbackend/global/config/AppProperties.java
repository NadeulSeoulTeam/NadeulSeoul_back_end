package com.alzal.nadeulseoulbackend.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@ConfigurationProperties(prefix = "app")
@Setter
public class AppProperties {
    private final Auth auth = new Auth();
    private final OAuth2 oAuth2 = new OAuth2();

//    private String authorizedRedirectUri;
//    private List<String> domain;

    @Getter
    @Setter
    public static class Auth{
        private String tokenSecret;
        private long tokenExpirationMsec;
    }

    @Getter
    @Setter
    public static class OAuth2 {
        private List<String> authorizedRedirectUris = new ArrayList<>();
    }


}