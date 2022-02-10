package com.alzal.nadeulseoulbackend.domain.mypage.repository;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.Follow;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.FollowInfo;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MypageRepository extends JpaRepository<FollowInfo, Follow> {
    Optional<FollowInfo> findByFolloweeAndFollower(User followee, User follower);
}
