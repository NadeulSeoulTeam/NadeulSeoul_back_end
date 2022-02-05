package com.alzal.nadeulseoulbackend.domain.inqury.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.models.auth.In;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDateTime;

@ApiModel("문의 사항 세부내용")
@Builder
@Data
public class InquryInfoDto {
    //private Long questionSeq;
    private Long memberSeq;
    private String questionTitle;
    private String question;
    private LocalDateTime questionDate;
    private String answer;
    private LocalDateTime answerDate;

}
