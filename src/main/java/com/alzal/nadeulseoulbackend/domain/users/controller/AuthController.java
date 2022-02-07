package com.alzal.nadeulseoulbackend.domain.users.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/member")
@RequiredArgsConstructor
public class AuthController {
    private final static String REFRESH_TOKEN = "refresh_token";


}
