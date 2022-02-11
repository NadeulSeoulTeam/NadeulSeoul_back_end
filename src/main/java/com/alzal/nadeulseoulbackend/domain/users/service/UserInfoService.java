package com.alzal.nadeulseoulbackend.domain.users.service;

import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import com.alzal.nadeulseoulbackend.global.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserInfoService {

    private SignupInfo signupInfo;
    private UserRepository userRepository;
    private TokenProvider tokenProvider;

    //User정보 등록하기
    public void updateSignupInfo (SignupInfo signupInfo,String token){
        Long tokenId = tokenProvider.getUserIdFromToken(token);

        Optional<User> user = userRepository.findById(tokenId).map(entity -> entity.update(signupInfo.getNickname(),signupInfo.getEmoji()));

    }

}
