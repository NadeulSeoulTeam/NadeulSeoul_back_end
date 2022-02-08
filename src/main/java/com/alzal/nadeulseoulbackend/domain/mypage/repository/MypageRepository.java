package com.alzal.nadeulseoulbackend.domain.mypage.repository;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.Follow;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.FollowInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MypageRepository extends JpaRepository<FollowInfo, Follow> {

    // 팔로잉 리스트 가져오기
    List<FollowInfo> findByFollowFolloweeSeq(Long followerSeq);

    // 팔로워 리스트 가져오기
    List<FollowInfo> findByFollowFollowerSeq(Long followerSeq);

    // 언팔로우 하기
    Optional<FollowInfo> findByFollowFolloweeSeqAndFollowFollowerSeq(Long followeeSeq, Long followerSeq);
}
