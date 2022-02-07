package com.alzal.nadeulseoulbackend.mypage.repository;

import com.alzal.nadeulseoulbackend.mypage.entity.Follow;
import com.alzal.nadeulseoulbackend.mypage.entity.FollowList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MypageRepository extends JpaRepository<FollowList, Follow> {

}
