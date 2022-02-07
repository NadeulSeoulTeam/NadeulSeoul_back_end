package com.alzal.nadeulseoulbackend.domain.mypage.service;

import com.alzal.nadeulseoulbackend.domain.mypage.dto.FollowDto;
import com.alzal.nadeulseoulbackend.domain.mypage.dto.FollowDtoList;
import com.alzal.nadeulseoulbackend.domain.mypage.dto.MypageInfoDto;;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.Follow;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.FollowInfo;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.Member;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.FollowInfoNotFoundException;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.MemberNotFoundException;
import com.alzal.nadeulseoulbackend.domain.mypage.repository.MemberRepository;
import com.alzal.nadeulseoulbackend.domain.mypage.repository.MypageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MypageService {

    @Autowired
    MypageRepository mypageRepository;

    @Autowired
    MemberRepository memberRepository;

    // 마이페이지 정보 가져오기
    public MypageInfoDto getMypageInfo (Long memberSeq){
        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));

        return MypageInfoDto.builder()
                .memberSeq(memberSeq)
                .nickName(member.getNickName())
                .emoji(member.getEmoji())
                .followeeCount(member.getFolloweeCount())
                .followerCount(member.getFollowerCount())
                .build();
    }

    // 팔로잉 리스트 가져오기
    public FollowDtoList getFollowingList (Long memberSeq){
        List<FollowInfo> followInfoList = mypageRepository.findByFollowFolloweeSeq(memberSeq);
                //.orElseThrow(() -> new IllegalArgumentException("팔로잉한 사람이 없습니다."));

        if(followInfoList.isEmpty()) {}

        List<FollowDto> followDtos = new ArrayList<>();
        for (FollowInfo followInfo : followInfoList) {
            Follow follow = followInfo.getFollow();
            followDtos.add(new FollowDto(follow.getFolloweeSeq(),follow.getFollowerSeq()));
        }

        return FollowDtoList.builder()
                .followDtoList(followDtos)
                .build();
    }

    // 팔로워 리스트 가져오기
    public FollowDtoList getFollowerList (Long memberSeq){
        List<FollowInfo> followInfoList = mypageRepository.findByFollowFollowerSeq(memberSeq);
                //.orElseThrow(() -> new IllegalArgumentException("팔로잉한 사람이 없습니다."));

        if(followInfoList.isEmpty()) {}

        List<FollowDto> followDtos = new ArrayList<>();
        for (FollowInfo followInfo : followInfoList) {
            Follow follow = followInfo.getFollow();
            followDtos.add(new FollowDto(follow.getFolloweeSeq(),follow.getFollowerSeq()));
        }

        return FollowDtoList.builder()
                .followDtoList(followDtos)
                .build();
    }

    // 팔로우 하기
    @Transactional
    public void insertFollow (Long memberSeq, Long followedMemberSeq){
        //팔로우 테이블에 저장
        Follow follow = new Follow(memberSeq, followedMemberSeq);
        FollowInfo followInfoEntity = mypageRepository.save(FollowInfo.builder()
                                                    .follow(follow)
                                                    .build());

        // 회원테이블에 팔로잉 수 업데이트 (증가)
        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));
        member.addFollowee();

        // 팔로우한 사람의 회원테이블에 팔로워 수 없데이트 (증가)
        Member followedMember = memberRepository.findByMemberSeq(followedMemberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));
        followedMember.addFollower();
    }

    // 언팔로우 하기
    @Transactional
    public void deleteFollow (Long memberSeq, Long followedMemberSeq){
        //팔로우 테이블에서 삭제
        FollowInfo followInfo = mypageRepository.findByFollowFolloweeSeqAndFollowFollowerSeq(memberSeq, followedMemberSeq)
                .orElseThrow(() -> new FollowInfoNotFoundException("팔로우 내역이 존재하지 않습니다."));
        mypageRepository.delete(followInfo);

        // 회원테이블에 팔로잉 수 업데이트 (감소)
        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));
        member.deleteFollowee();

        // 팔로우한 사람의 회원테이블에 팔로워 수 업데이트 (감소)
        Member followedMember = memberRepository.findByMemberSeq(followedMemberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));
        followedMember.deleteFollower();
    }


}
