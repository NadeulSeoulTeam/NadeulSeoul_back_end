package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CurationDto {
    private Long curationSeq;
    private String title;
    private Integer budget;
    private Integer personnel;
    private String desc;
    private LocalDateTime date;
    private Integer good;
    private Integer views;
    private Long memberSeq;
    private Integer photoCount;

    @Builder

    public CurationDto(Long curationSeq, String title, Integer budget, Integer personnel,
                       String desc, LocalDateTime date, Integer good, Integer views,
                       Long memberSeq, Integer photoCount) {
        this.curationSeq = curationSeq;
        this.title = title;
        this.budget = budget;
        this.personnel = personnel;
        this.desc = desc;
        this.date = date;
        this.good = good;
        this.views = views;
        this.memberSeq = memberSeq;
        this.photoCount = photoCount;
    }

    public Curation toEntity() {
        return Curation.builder()
                .curationSeq(curationSeq)
                .title(title)
                .budget(budget)
                .personnel(personnel)
                .desc(desc)
                .good(good)
                .views(views)
                .memberSeq(memberSeq)
                .photoCount(photoCount)
                .build();
    }

    public static CurationDto fromEntity(Curation curation) {
        return CurationDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .budget(curation.getBudget())
                .personnel(curation.getPersonnel())
                .desc(curation.getDesc())
                .good(curation.getGood())
                .views(curation.getViews())
                .memberSeq(curation.getMemberSeq())
                .photoCount(curation.getPhotoCount())
                .build();
    }
}
