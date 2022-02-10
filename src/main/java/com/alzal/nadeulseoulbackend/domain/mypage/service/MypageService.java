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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
    public List<FollowDto> getFollowingList (Long memberSeq){
        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));

        Set<FollowInfo> followeeList = member.getFolloweeList();

        List<FollowDto> followDtoList = new ArrayList<>();
        Iterator<FollowInfo> iter = followeeList.iterator();
        while (iter.hasNext()){
            Member followee = iter.next().getFollowee();
            followDtoList.add(new FollowDto(followee.getMemberSeq(), followee.getNickName(),followee.getEmoji()));
        }

        return followDtoList;
    }

    // 팔로워 리스트 가져오기
    public List<FollowDto> getFollowerList (Long memberSeq){
        Member member = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("해당 사용자가 존재하지 않습니다."));

        Set<FollowInfo> followerList = member.getFollowerList();

        List<FollowDto> followDtoList = new ArrayList<>();
        Iterator<FollowInfo> iter = followerList.iterator();
        while (iter.hasNext()){
            Member follower = iter.next().getFollowee();
            followDtoList.add(new FollowDto(follower.getMemberSeq(), follower.getNickName(),follower.getEmoji()));
        }

        return followDtoList;

    }

    // 팔로우 하기
    @Transactional
    public void insertFollow (Long memberSeq, Long followedMemberSeq){
        //followee와 follower seq에 해당하는 Member를 조회 한 후 tb_follow 에 저장
        Member followee = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("followee 가 존재하지 않습니다."));

        Member follower = memberRepository.findByMemberSeq(followedMemberSeq)
                .orElseThrow(() -> new MemberNotFoundException("follower 가 존재하지 않습니다."));

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
    public void deleteFollow (Long memberSeq, Long followedMemberSeq){
        //followee와 follower seq에 해당하는 Member를 조회 한 후 tb_follow 에 저장
        Member followee = memberRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new MemberNotFoundException("followee 가 존재하지 않습니다."));

        Member follower = memberRepository.findByMemberSeq(followedMemberSeq)
                .orElseThrow(() -> new MemberNotFoundException("follower 가 존재하지 않습니다."));

        //팔로우 테이블에서 삭제
        FollowInfo followInfo = mypageRepository.findByFolloweeAndFollower(followee, follower)
                .orElseThrow(() -> new FollowInfoNotFoundException("팔로우 내역이 존재하지 않습니다."));
        mypageRepository.delete(followInfo);
        System.out.println(followInfo.toString());
        // 회원테이블에 팔로잉 수 업데이트 (감소)
        followee.deleteFollowee();

        // 팔로우한 사람의 회원테이블에 팔로워 수 업데이트 (감소)
        follower.deleteFollower();

    }


}
