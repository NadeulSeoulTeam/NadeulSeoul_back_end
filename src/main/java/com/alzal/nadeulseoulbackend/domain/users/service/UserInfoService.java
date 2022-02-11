package com.alzal.nadeulseoulbackend.domain.users.service;

import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import com.alzal.nadeulseoulbackend.global.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenProvider tokenProvider;

    //User정보 등록하기
    public void updateSignupInfo (SignupInfo signupInfo){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<User> user = userRepository.findByEmail(email).map(entity -> entity.update(signupInfo.getNickname(),signupInfo.getEmoji()));
//        if(user==null){
//            throw NotExistedUserException();
//        }
        userRepository.save(user.get());
    }

}
