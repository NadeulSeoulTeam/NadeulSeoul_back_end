package com.alzal.nadeulseoulbackend.domain.mypage.repository;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.Follow;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.FollowInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MypageRepository extends JpaRepository<FollowInfo, Follow> {

    // 팔로잉 리스트 가져오기
    //@Query("select f from FollowInfo f where f.follow.followeeSeq= :followeeSeq")
    Optional<List<FollowInfo>> findByFollowInfoFolloweeSeq(Long followeeSeq);

    // 팔로워 리스트 가져오기
    //@Query("select f from FollowInfo f  where f.follow.followerSeq= :followerSeq")
    Optional<List<FollowInfo>> findByFollowInfoFollowerSeq(Long followerSeq);

    // 언팔로우 하기
    //@Query("select f from FollowInfo f where f.follow.followeeSeq= :followeeSeq and f.follow.followerSeq= :followerSeq")
    //Optional<FollowInfo> findByFolloweeSeqAndFollowerSeq(Long followeeSeq, Long followerSeq);
}
