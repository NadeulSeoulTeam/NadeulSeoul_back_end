package com.alzal.nadeulseoulbackend.domain.inqury.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@ApiModel("문의 사항 세부내용")
public class InquryInfoDto {
    //private Long questionSeq;
    private Long memberSeq;
    private String questionTitle;
    private String question;
    private LocalDateTime questionDate;
    private String answer;
    private LocalDateTime answerDate;
}


