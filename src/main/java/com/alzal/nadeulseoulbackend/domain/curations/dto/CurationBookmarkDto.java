package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CurationBookmarkDto {
    private Long curationSeq;
    private String title;
    private Integer good;
    private Long thumnail;

    static public CurationBookmarkDto fromEntity(Curation curation) {
        return CurationBookmarkDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .good(curation.getGood())
                .thumnail(curation.getThumnail())
                .build();
    }
}
