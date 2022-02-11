package com.alzal.nadeulseoulbackend.domain.mypage.repository;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // 마이페이지 정보 가져오기
    Optional<User> findByUserSeq(Long memberSeq);
}