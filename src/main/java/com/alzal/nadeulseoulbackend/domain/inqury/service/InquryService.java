package com.alzal.nadeulseoulbackend.domain.inqury.service;

import com.alzal.nadeulseoulbackend.domain.inqury.dto.*;
import com.alzal.nadeulseoulbackend.domain.inqury.entity.Inqury;
import com.alzal.nadeulseoulbackend.domain.inqury.exception.InquryNotFoundException;
import com.alzal.nadeulseoulbackend.domain.inqury.repository.InquryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class InquryService {

    @Autowired
    private InquryRepository inquryRepository;

    // 문의 사항 목록 가져오기
    public InquryDtoList getInquryList(Long memberSeq) {
        List<Inqury> inquryList = inquryRepository.findByMemberSeq(memberSeq)
                .orElseThrow(() -> new InquryNotFoundException("문의 사항이"));

        List<InquryDto> inquryDtos = new ArrayList<>();
        for (int i = 0; i < inquryList.size(); i++) {
            inquryDtos.add(new InquryDto(inquryList.get(i).getQuestionSeq(), inquryList.get(i).getQuestion(), inquryList.get(i).getQuestionDate()));
        }

        return InquryDtoList.builder()
                .inquryDtoList(inquryDtos)
                .build();
    }

    //문의 사항 내용 가져오기
    public InquryInfoDto getInqury(Long questionSeq) {
        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquryNotFoundException("문의 사항이"));

        return InquryInfoDto.builder()
                .questionTitle(inqury.getQuestionTitle())
                .question(inqury.getQuestion())
                .questionDate(inqury.getQuestionDate())
                .answer(inqury.getAnswer())
                .answerDate(inqury.getAnswerDate())
                .build();
    }

    //문의 사항 작성하기
    @Transactional
    public int insertInqury(InquryInfoDto inquryInfoDto) {

        Inqury inquryEntity = inquryRepository.save(Inqury.builder()
                                            .memberSeq(inquryInfoDto.getMemberSeq())
                                            .questionTitle(inquryInfoDto.getQuestionTitle())
                                            .question(inquryInfoDto.getQuestion())
                                            .questionDate(LocalDateTime.now())
                                            .build()); // answer값 들어가지 않아서 자동 null로 저장

        return inquryEntity != null? 1 : 0;
    }

    // 문의 사항 수정하기
    @Transactional
    public void updateInqury(Long questionSeq, InquryInfoDto inquryInfoDto) {

        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquryNotFoundException("문의 사항이"));

        inqury.update(inquryInfoDto.getQuestionTitle(), inquryInfoDto.getQuestion());
    }

    // 문의 사항 삭제하기
    // 만약 답글이 달려있는 경우 삭제 불가
    @Transactional
    public int hiddenInqury(Long questionSeq) {

        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new IllegalArgumentException ("문의 사항이 존재하지 않습니다."));

        // 이미 삭제 처리된 문의 사항일 경우
        if(inqury.isHidden()) return 0;

        // 답글이 있는 경우 삭제 불가
        if(inqury.getAnswer() != null) return 1;

        inqury.setHidden(true);
        return 2;
    }

    //문의 사항 답변 등록하기
    @Transactional
    public int insertAnswer(AnswerDto answerDto) {
        Inqury inqury = inquryRepository.findByQuestionSeq(answerDto.getQuestionSeq())
                .orElseThrow(() -> new InquryNotFoundException("문의 사항이"));

        // 이미 답변이 있다면 더이상 답변 불가 -> 이걸 어떻게 처리하는게 좋을지 따로 exception을 만들지 or string으로 보내줄지?
        if(inqury.getAnswer() != null) {
            return 0;
        }

        inqury.updateAnswer(answerDto.getAnswer());
        return 1;
    }

    //문의 사항 답변 수정하기
    @Transactional
    public void updateAnswer(AnswerDto answerDto) {
        Inqury inqury = inquryRepository.findByQuestionSeq(answerDto.getQuestionSeq())
                .orElseThrow(() -> new InquryNotFoundException("문의 사항이"));

        // 문의 사항이 삭제 처리 됐다면

        // 문의 사항에 등록된 답변이 없다면

        inqury.updateAnswer(answerDto.getAnswer());
        System.out.println(inqury.getAnswer());
    }

    //문의 사항 답변 삭제하기
    @Transactional
    public void deleteAnswer(Long questionSeq) {
        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq)
                .orElseThrow(() -> new InquryNotFoundException("문의 사항이"));

        // 삭제대신 null처리
        inqury.updateAnswer(null);
    }


}
