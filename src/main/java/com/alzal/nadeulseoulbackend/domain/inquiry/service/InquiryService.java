package com.alzal.nadeulseoulbackend.domain.inquiry.service;

import com.alzal.nadeulseoulbackend.domain.inquiry.dto.AnswerDto;
import com.alzal.nadeulseoulbackend.domain.inquiry.dto.InquiryDto;
import com.alzal.nadeulseoulbackend.domain.inquiry.dto.InquiryDtoList;
import com.alzal.nadeulseoulbackend.domain.inquiry.dto.InquiryInfoDto;
import com.alzal.nadeulseoulbackend.domain.inquiry.entity.Inquiry;
import com.alzal.nadeulseoulbackend.domain.inquiry.exception.AlreadyDeletedInquiryException;
import com.alzal.nadeulseoulbackend.domain.inquiry.exception.AnswerExistenceException;
import com.alzal.nadeulseoulbackend.domain.inquiry.exception.AnswerNotFoundException;
import com.alzal.nadeulseoulbackend.domain.inquiry.exception.InquiryNotFoundException;
import com.alzal.nadeulseoulbackend.domain.inquiry.repository.InquiryRepository;
import com.alzal.nadeulseoulbackend.domain.mypage.exception.UserNotFoundException;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import com.alzal.nadeulseoulbackend.domain.users.repository.UserRepository;
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

    @Autowired
    private UserRepository userRepository;

    // 문의 사항 목록 가져오기
    public InquiryDtoList getInquiryList(Long userSeq) {
        User user = userRepository.findById(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        List<Inquiry> inquiryList = user.getInquiryList();
        List<InquiryDto> inquiryDtoList = new ArrayList<>();

        for (Inquiry inquiry : inquiryList) {
            if (inquiry.isHidden()) {
                continue;
            }

            inquiryDtoList.add(InquiryDto.builder()
                    .questionSeq(inquiry.getQuestionSeq())
                    .questionTitle(inquiry.getQuestionTitle())
                    .questionDate(inquiry.getQuestionDate())
                    .build());
        }

        return InquiryDtoList.builder()
                .inquiryDtoList(inquiryDtoList)
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
    public void insertInquiry(Long userSeq, InquiryInfoDto inquiryInfoDto) {
        User user = userRepository.findByUserSeq(userSeq)
                .orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));

        inquiryRepository.save(Inquiry.builder()
                .user(user)
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
    public void hiddenInquiry(Long questionSeq) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new IllegalArgumentException("문의 사항이 존재하지 않습니다."));

        // 이미 삭제 처리된 문의 사항일 경우
        if (inquiry.isHidden()) {
            throw new AlreadyDeletedInquiryException("잘 못된 요청입니다.");
        }

        // 답글이 있는 경우 삭제 불가
        if (inquiry.getAnswer() != null){
            throw new AnswerExistenceException("답글이 있는 경우 삭제가 불가합니다.");
        }

        inquiry.setHidden(true);
    }

    //문의 사항 답변 등록하기
    @Transactional
    public void insertAnswer(AnswerDto answerDto) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(answerDto.getQuestionSeq())
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이 존재하지 않습니다."));


        if (inquiry.getAnswer() != null) {
            throw new AnswerExistenceException("잘 못된 요청입니다.");
        }
      
        inquiry.updateAnswer(answerDto.getAnswer());
    }

    //문의 사항 답변 수정하기
    @Transactional
    public void updateAnswer(AnswerDto answerDto) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(answerDto.getQuestionSeq())
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이 존재하지 않습니다."));
        // 문의 사항이 삭제 처리 됐다면
        if(inquiry.isHidden()){
            throw new AlreadyDeletedInquiryException("잘 못된 요청입니다.");
        }
        // 문의 사항에 등록된 답변이 없다면
        if(inquiry.getAnswer() == null){
            throw new AnswerNotFoundException("잘 못된 요청입니다.");
        }
        inquiry.updateAnswer(answerDto.getAnswer());
    }

    //문의 사항 답변 삭제하기
    @Transactional
    public void deleteAnswer(Long questionSeq) {
        Inquiry inquiry = inquiryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquiryNotFoundException("문의 사항이 존재하지 않습니다."));

        // 삭제대신 null 처리
        inquiry.updateAnswer(null);
    }
}
