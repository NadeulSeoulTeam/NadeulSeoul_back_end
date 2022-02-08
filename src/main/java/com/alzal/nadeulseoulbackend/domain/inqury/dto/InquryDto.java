package com.alzal.nadeulseoulbackend.domain.inqury.dto;

import io.swagger.annotations.ApiModel;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@ApiModel("문의사항 목록 정보")
public class InquryDto {
    private Long questionSeq;
    private String questionTitle;
    private LocalDateTime questionDate;

    public InquryDto(Long questionSeq, String questionTitle, LocalDateTime questionDate) {
        this.questionSeq = questionSeq;
        this.questionTitle = questionTitle;
        this.questionDate = questionDate;
    }
}
