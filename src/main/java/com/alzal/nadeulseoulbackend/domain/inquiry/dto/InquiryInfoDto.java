package com.alzal.nadeulseoulbackend.domain.inquiry.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ApiModel("문의 사항 세부내용")
public class InquiryInfoDto {
    private Long userSeq;
    private String questionTitle;
    private String question;
    private LocalDateTime questionDate;
    private String answer;
    private LocalDateTime answerDate;
}


