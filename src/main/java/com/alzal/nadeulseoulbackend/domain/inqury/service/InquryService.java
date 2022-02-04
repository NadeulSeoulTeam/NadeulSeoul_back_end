package com.alzal.nadeulseoulbackend.domain.inqury.service;

import com.alzal.nadeulseoulbackend.domain.inqury.dto.Inqury;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryInfoDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InqurylistDto;
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
    public InqurylistDto getInquryList(Long memberSeq) {
        List<Inqury> inquryList = inquryRepository.findByMemberSeq(memberSeq);
        List<InquryDto> inquryDtos = new ArrayList<>();

        // 문의사항 없을 때 예외 처리 필요
        if(inquryList.size() == 0){

        }

        return InqurylistDto.builder()
                .inquryDtoList(inquryDtos)
                .build();
    }

    //문의 사항 가져오기
    public InquryInfoDto getInqury(Long questionSeq) {
        Inqury inqury = inquryRepository.findByQuestionSeq(questionSeq);

        //문의 사항 없을 때 예외처리
        if (inqury == null){

        }

        return InquryInfoDto.builder()
                .questionSeq(inqury.getQuestionSeq())
                .questionTitle(inqury.getQuestionTitle())
                .questionDate(inqury.getQuestionDate())
                .build();
    }

    //문의 사항 작성하기
    public int saveInqury(Inqury inqury) {

        Inqury inquryEntity = inquryRepository.save(Inqury.builder()
                                            .memberSeq(inqury.getMemberSeq())
                                            .questionTitle(inqury.getQuestionTitle())
                                            .question(inqury.getQuestion())
                                            .questionDate(LocalDateTime.now())
                                            .build());

        return inquryEntity != null? 1 : 0;
    }

}
