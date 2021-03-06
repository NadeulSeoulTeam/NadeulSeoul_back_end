package com.alzal.nadeulseoulbackend.domain.inquiry.repository;

import com.alzal.nadeulseoulbackend.domain.inquiry.entity.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

    //문의 사항 가져오기
    Optional<Inquiry> findByQuestionSeq(Long questionSeq);

}