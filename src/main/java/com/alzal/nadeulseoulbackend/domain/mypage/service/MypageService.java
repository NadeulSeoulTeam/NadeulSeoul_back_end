package com.alzal.nadeulseoulbackend.domain.mypage.service;

import com.alzal.nadeulseoulbackend.domain.mypage.dto.FollowDto;
import com.alzal.nadeulseoulbackend.domain.mypage.dto.MypageInfoDto;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.FollowInfo;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.FollowInfoExistenceException;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.FollowInfoNotFoundException;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.mypage.repository.MypageRepository;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class MypageService {

    @Autowired
    MypageRepository mypageRepository;

    @Autowired
    UserRepository userRepository;

    // 마이페이지 정보 가져오기
    public MypageInfoDto getMypageInfo(Long userSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));

        return MypageInfoDto.builder()
                .userSeq(userSeq)
                .nickName(user.getNickname())
                .emoji(user.getEmoji())
                .followeeCount(user.getFolloweeCount())
                .followerCount(user.getFollowerCount())
                .build();
    }

    // 팔로잉 리스트 가져오기
    public List<FollowDto> getFollowingList(Long userSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));

        Set<FollowInfo> followeeList = user.getFolloweeList();

        List<FollowDto> followDtoList = new ArrayList<>();
        Iterator<FollowInfo> iter = followeeList.iterator();
        while (iter.hasNext()) {
            User follower = iter.next().getFollower();
            followDtoList.add(new FollowDto(follower.getUserSeq(), follower.getNickname(), follower.getEmoji()));
        }

        return followDtoList;
    }

    // 팔로워 리스트 가져오기
    public List<FollowDto> getFollowerList(Long userSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 사용자가 존재하지 않습니다."));

        Set<FollowInfo> followerList = user.getFollowerList();

        List<FollowDto> followDtoList = new ArrayList<>();
        Iterator<FollowInfo> iter = followerList.iterator();
        while (iter.hasNext()) {
            User followee = iter.next().getFollowee();
            followDtoList.add(new FollowDto(followee.getUserSeq(), followee.getNickname(), followee.getEmoji()));
        }

        return followDtoList;
    }

    // 팔로우 하기
    @Transactional
    public void insertFollow(Long userSeq, Long followedUserSeq) {
        //followee와 follower seq에 해당하는 User를 조회 한 후 tb_follow 에 저장
        User followee = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다."));

        User follower = userRepository.findById(followedUserSeq)
                .orElseThrow(() -> new UserNotFoundException("사용자가 존재하지 않습니다."));

        // 이미 팔로우된 사람인지 확인
        Optional<FollowInfo> followInfo = mypageRepository.findByFolloweeAndFollower(followee, follower);
        followInfo.ifPresent(f -> {
            throw new FollowInfoExistenceException("이미 팔로우한 사용자 입니다.");
        });

        //팔로우 테이블에 저장
        FollowInfo followInfoEntity = mypageRepository.save(
                FollowInfo.builder()
                        .followee(followee)
                        .follower(follower)
                        .build()
        );

        // 회원테이블에 팔로잉 수 업데이트 (증가)
        followee.addFollowee();
        // 팔로우한 사람의 회원테이블에 팔로워 수 없데이트 (증가)
        follower.addFollower();
    }

    // 언팔로우 하기
    @Transactional
    public void deleteFollow(Long userSeq, Long followedUserSeq) {
        //followee와 follower seq에 해당하는 User 조회 한 후 tb_follow 에 저장
        User followee = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("followee 가 존재하지 않습니다."));

        User follower = userRepository.findById(followedUserSeq)
                .orElseThrow(() -> new UserNotFoundException("follower 가 존재하지 않습니다."));

        //팔로우 테이블에서 삭제
        FollowInfo followInfo = mypageRepository.findByFolloweeAndFollower(followee, follower)
                .orElseThrow(() -> new FollowInfoNotFoundException("팔로우 내역이 존재하지 않습니다."));
        mypageRepository.delete(followInfo);

        // 회원테이블에 팔로잉 수 업데이트 (감소)
        followee.deleteFollowee();

        // 팔로우한 사람의 회원테이블에 팔로워 수 업데이트 (감소)
        follower.deleteFollower();
    }
}
