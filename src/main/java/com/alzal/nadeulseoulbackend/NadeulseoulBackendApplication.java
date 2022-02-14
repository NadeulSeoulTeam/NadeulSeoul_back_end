package com.alzal.nadeulseoulbackend;

import com.alzal.nadeulseoulbackend.global.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(value = {AppProperties.class})
@SpringBootApplication
public class NadeulseoulBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(NadeulseoulBackendApplication.class, args);
    }

}
