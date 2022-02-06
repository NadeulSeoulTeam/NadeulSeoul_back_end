package com.alzal.nadeulseoulbackend.domain.inqury.repository;

import com.alzal.nadeulseoulbackend.domain.inqury.entity.Inqury;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InquryRepository extends JpaRepository<Inqury, Long> {

    // 문의 목록 가져오기
    Optional<List<Inqury>> findByMemberSeq(Long memberSeq);

    //문의 사항 가져오기
    Optional<Inqury> findByQuestionSeq (Long questionSeq);

}
