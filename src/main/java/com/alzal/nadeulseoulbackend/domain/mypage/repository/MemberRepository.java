package com.alzal.nadeulseoulbackend.domain.mypage.repository;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // 마이페이지 정보 가져오기
    Optional<Member> findByMemberSeq(Long memberSeq);
}