package com.alzal.nadeulseoulbackend.domain.inquiry.service;

import com.alzal.nadeulseoulbackend.domain.inquiry.dto.*;
import com.alzal.nadeulseoulbackend.domain.inquiry.entity.Inquiry;
import com.alzal.nadeulseoulbackend.domain.inquiry.exception.InquiryNotFoundException;
import com.alzal.nadeulseoulbackend.domain.inquiry.repository.InquiryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class InquiryService {

    @Autowired
    private InquiryRepository inquiryRepository;

    // 문의 사항 목록 가져오기
    public InquiryDtoList getInquiryList(Long memberSeq) {
        List<Inquiry> inquiryList = inquiryRepository.findByMemberSeqAndHiddenIsFalse(memberSeq)
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이"));

        List<InquiryDto> inquiryDtos = new ArrayList<>();
        for (int i = 0; i < inquiryList.size(); i++) {
                inquiryDtos.add(new InquiryDto(inquiryList.get(i).getQuestionSeq(), inquiryList.get(i).getQuestionTitle(), inquiryList.get(i).getQuestionDate()));
        }

        return InquiryDtoList.builder()
                .inquiryDtoList(inquiryDtos)
                .build();
    }

    //문의 사항 내용 가져오기
    public InquiryInfoDto getInquiry(Long questionSeq) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이"));

        return InquiryInfoDto.builder()
                .questionTitle(inquiry.getQuestionTitle())
                .question(inquiry.getQuestion())
                .questionDate(inquiry.getQuestionDate())
                .answer(inquiry.getAnswer())
                .answerDate(inquiry.getAnswerDate())
                .build();
    }

    //문의 사항 작성하기
    @Transactional
    public void insertInquiry(InquiryInfoDto inquiryInfoDto) {

        // 글자 수 예외 처리

        Inquiry inquiryEntity = inquiryRepository.save(Inquiry.builder()
                                            .memberSeq(inquiryInfoDto.getMemberSeq())
                                            .questionTitle(inquiryInfoDto.getQuestionTitle())
                                            .question(inquiryInfoDto.getQuestion())
                                            .questionDate(LocalDateTime.now())
                                            .build()); // answer값 들어가지 않아서 자동 null로 저장
    }

    // 문의 사항 수정하기
    @Transactional
    public void updateInquiry(Long questionSeq, InquiryInfoDto inquiryInfoDto) {

        Inquiry inquiry = inquiryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이"));

        inquiry.update(inquiryInfoDto.getQuestionTitle(), inquiryInfoDto.getQuestion());
    }

    // 문의 사항 삭제하기
    // 만약 답글이 달려있는 경우 삭제 불가
    @Transactional
    public int hiddenInquiry(Long questionSeq) {

        Inquiry inquiry = inquiryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new IllegalArgumentException ("문의 사항이 존재하지 않습니다."));

        // 이미 삭제 처리된 문의 사항일 경우
        if(inquiry.isHidden()) return 0;

        // 답글이 있는 경우 삭제 불가
        if(inquiry.getAnswer() != null) return 1;

        inquiry.setHidden(true);
        return 2;
    }

    //문의 사항 답변 등록하기
    @Transactional
    public int insertAnswer(AnswerDto answerDto) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(answerDto.getQuestionSeq())
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이"));

        // 이미 답변이 있다면 더이상 답변 불가 -> 이걸 어떻게 처리하는게 좋을지 따로 exception을 만들지 or string으로 보내줄지?
        if(inquiry.getAnswer() != null) {
            return 0;
        }

        inquiry.updateAnswer(answerDto.getAnswer());
        return 1;
    }

    //문의 사항 답변 수정하기
    @Transactional
    public void updateAnswer(AnswerDto answerDto) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(answerDto.getQuestionSeq())
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이"));

        // 문의 사항이 삭제 처리 됐다면

        // 문의 사항에 등록된 답변이 없다면

        inquiry.updateAnswer(answerDto.getAnswer());
        System.out.println(inquiry.getAnswer());
    }

    //문의 사항 답변 삭제하기
    @Transactional
    public void deleteAnswer(Long questionSeq) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이"));

        // 삭제대신 null 처리
        inquiry.updateAnswer(null);
    }


}
