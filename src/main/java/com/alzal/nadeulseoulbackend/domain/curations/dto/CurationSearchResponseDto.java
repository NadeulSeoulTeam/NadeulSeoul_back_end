package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import lombok.Builder;
import lombok.Data;

@Data
public class CurationSearchResponseDto {
    private Long curationSeq;
    private String title;
    private Integer good;
    private Long thumnail;

    @Builder
    public CurationSearchResponseDto(Long curationSeq, String title, Integer good, Long thumnail) {
        this.curationSeq = curationSeq;
        this.title = title;
        this.good = good;
        this.thumnail = thumnail;
    }

    public static CurationSearchResponseDto fromEntity(Curation curation){
        return CurationSearchResponseDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .good(curation.getGood())
                .thumnail(curation.getThumnail())
                .build();

    }
}
