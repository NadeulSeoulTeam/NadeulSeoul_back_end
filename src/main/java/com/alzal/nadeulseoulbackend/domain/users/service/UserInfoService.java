package com.alzal.nadeulseoulbackend.domain.users.service;

import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;

    //User정보 등록하기
    @Transactional
    public void updateSignupInfo (SignupInfo signupInfo){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByEmail(email).map(entity -> entity.update(signupInfo.getNickname(),signupInfo.getEmoji())).orElseGet(User::new);
        System.out.println(user.getNickname());

        userRepository.save(user);
    }

}
