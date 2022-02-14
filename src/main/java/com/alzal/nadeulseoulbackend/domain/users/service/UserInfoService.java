package com.alzal.nadeulseoulbackend.domain.users.service;

import com.alzal.nadeulseoulbackend.domain.users.dto.AssignedUserDto;
import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfoDto;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.exception.DuplicatedNicknameException;
import com.alzal.nadeulseoulbackend.domain.users.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import com.alzal.nadeulseoulbackend.global.auth.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;
    private Long id;

    //User정보 등록하기
    @Transactional
    public AssignedUserDto updateSignupInfo (SignupInfoDto signupInfo){

        Long id = getId();
        User user = userRepository.findById(id).map(entity -> entity.update(signupInfo.getNickname(),signupInfo.getEmoji())).orElseGet(User::new);
        userRepository.save(user);
        AssignedUserDto assignedUserDto = getAssignedUserInfo();
        return assignedUserDto;
    }


    public Long getId(){
        UserPrincipal userPrincipal = (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        id = userPrincipal.getId();
        return id;
    }

    public AssignedUserDto getAssignedUserInfo() {

        Long id = getId();
        User user = userRepository.findById(id).orElseThrow(()-> new UserNotFoundException("받아온 id로는 유저를 찾을 수 없습니다."));
        AssignedUserDto assignedUserDto = AssignedUserDto.builder().userSeq(user.getUserSeq()).nickname(user.getNickname()).role(user.getRoleKey()).followeeCount(user.getFolloweeCount()).follwerCount(user.getFollowerCount()).build();
        return assignedUserDto;
    }


    public void checkNicknameDuplication(String nickname){
        System.out.println(userRepository.existsByNickname(nickname));
        if(userRepository.existsByNickname(nickname)){
            throw new DuplicatedNicknameException("중복된 닉네임입니다.");
        }
    }

}
