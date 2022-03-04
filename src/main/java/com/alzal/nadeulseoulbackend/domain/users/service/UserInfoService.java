package com.alzal.nadeulseoulbackend.domain.users.service;

import com.alzal.nadeulseoulbackend.domain.users.dto.AssignedUserDto;
import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfoDto;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.exception.CannotDeleteUserTokenInRedisException;
import com.alzal.nadeulseoulbackend.domain.users.exception.DifferentUserException;
import com.alzal.nadeulseoulbackend.domain.users.exception.DuplicatedNicknameException;
import com.alzal.nadeulseoulbackend.domain.users.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import com.alzal.nadeulseoulbackend.global.auth.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class UserInfoService {

    @Autowired
    private UserRepository userRepository;
    private Long id;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //User정보 등록하기
    @Transactional
    public AssignedUserDto updateSignupInfo(SignupInfoDto signupInfo) {

        Long id = getId();

        User user = userRepository.findById(id).map(entity -> entity.update(signupInfo.getNickname(), signupInfo.getEmoji())).orElseThrow(() -> new UserNotFoundException("구글에서 유저 정보를 받을 수 없습니다."));

        userRepository.save(user);
        AssignedUserDto assignedUserDto = getAssignedUserInfo();
        return assignedUserDto;
    }


    public Long getId() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        id = userPrincipal.getId();
        return id;
    }

    public AssignedUserDto getAssignedUserInfo() {

        Long id = getId();
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("받아온 id로는 유저를 찾을 수 없습니다."));
        AssignedUserDto assignedUserDto = AssignedUserDto.builder().userSeq(user.getUserSeq()).emoji(user.getEmoji()).nickname(user.getNickname()).role(user.getRoleKey()).followeeCount(user.getFolloweeCount()).followerCount(user.getFollowerCount()).build();
        return assignedUserDto;
    }


    public void checkNicknameDuplication(String nickname) {
        Long userId = getId();
        if (userRepository.existsByNickname(nickname)) { // 존재하면
            User user = userRepository.findByNickname(nickname).orElseGet(User::new);
            if (!userId.equals(user.getUserSeq())) {
                throw new DuplicatedNicknameException("중복된 닉네임입니다.");
            }
        }

        //        User user = userRepository.findById(getId()).orElseGet(User::new);
//        Long userId = getId();
//        if (userRepository.existsByNickname(nickname)) {
//            if(user.getUserSeq()!=userId) {
//                throw new DuplicatedNicknameException("중복된 닉네임입니다.");
//            }
//        }

    }

    public void signoutUserfromRedis() {
        String userId = Long.toString(getId());
        stringRedisTemplate.delete(userId);
        String valueForCheck = stringRedisTemplate.opsForValue().get(userId);
        if (valueForCheck != null) {
            throw new CannotDeleteUserTokenInRedisException("유저 토큰을 레디스에서 삭제할 수 없습니다.");
        }
    }

    @Transactional
    public void editUserInfo(SignupInfoDto signupInfo, String paramId) {
        Long Id = getId();
        if (!paramId.equals(Long.toString(Id))) throw new DifferentUserException("다른 사용자의 접근 입니다.");
        User user = userRepository.findById(Id).map(entity -> entity.update(signupInfo.getNickname(), signupInfo.getEmoji())).orElseThrow(() -> new UserNotFoundException("유저 정보를 찾을 수 없습니다."));

        userRepository.save(user);
    }
}
