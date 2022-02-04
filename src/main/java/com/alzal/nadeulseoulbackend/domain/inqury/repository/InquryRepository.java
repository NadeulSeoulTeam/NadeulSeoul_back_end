package com.alzal.nadeulseoulbackend.domain.inqury.repository;

import com.alzal.nadeulseoulbackend.domain.inqury.dto.Inqury;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquryRepository extends JpaRepository<Inqury, Long> {

    // 문의 목록 가져오기
    List<Inqury> findByMemberSeq(Long memberSeq);

    //문의 사항 가져오기
    Inqury findByQuestionSeq (Long questionSeq);


}
