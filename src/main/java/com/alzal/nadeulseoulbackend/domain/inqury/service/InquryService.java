package com.alzal.nadeulseoulbackend.domain.inqury.service;

import com.alzal.nadeulseoulbackend.domain.inqury.dto.Inqury;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryInfoDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryDtoList;
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
        List<Inqury> inquryList = inquryRepository.findByMemberSeq(memberSeq);
        List<InquryDto> inquryDtos = new ArrayList<>();

        // 문의사항 없을 때 예외 처리 필요
        if(inquryList.size() == 0){

        }

        return InquryDtoList.builder()
                .inquryDtoList(inquryDtos)
                .build();
    }

    //문의 사항 내용 가져오기
    public InquryInfoDto getInqury(Long questionSeq) {
        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq);

        //문의 사항 없을 때 예외처리
        if (inqury == null){

        }

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
                                            .build());

        return inquryEntity != null? 1 : 0;
    }

    // 문의 사항 수정하기
    @Transactional
    public int updateInqury(Long questionSeq, InquryInfoDto inquryInfoDto) {

        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq);

        // 해당 번호를 가진 문의 사항이 없을 때 처리
        if(inqury == null) return 0;

        inqury.update(inquryInfoDto.getQuestionTitle(), inquryInfoDto.getQuestion());
        return 1;
    }

    // 문의 사항 삭제하기
    // 만약 답글이 달려있는 경우 삭제 불가
    @Transactional
    public int hiddenInqury(Long questionSeq) {

        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq);

        // 해당 번호를 가진 문의 사항이 없을거나 이미 삭제 처리된 문의 사항일 경우
        if(inqury == null && inqury.isHidden()) return 0;

        // 답글이 있는 경우 삭제 불가
        if(inqury.getAnswer() != null) return 1;

        inqury.setHidden(true);
        return 2;
    }


}
