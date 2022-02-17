package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CurationResponseDto {
    private Long curationSeq;
    private Long memberSeq;
    private String title;
    private String description;
    private Integer personnel;
    private Integer budget;
    private LocalDateTime date;
    private Integer good;
    private Integer views;
    private Integer photoCount;
    private List<Long> fileList;
    private List<CodeDto> local;
    private List<CodeDto> theme;
    private List<CurationCourseInfoDto> curationCourse;

    @Builder
    public CurationResponseDto(Long curationSeq, Long memberSeq, String title, String description,
                               Integer personnel, Integer budget, LocalDateTime date, Integer good,
                               Integer views, Integer photoCount, List<CodeDto> local, List<CodeDto> theme,List<CurationCourseInfoDto> curationCourse) {
        this.curationSeq = curationSeq;
        this.memberSeq = memberSeq;
        this.title = title;
        this.description = description;
        this.personnel = personnel;
        this.budget = budget;
        this.date = date;
        this.good = good;
        this.views = views;
        this.curationCourse = curationCourse;
        this.photoCount = photoCount;
        this.local = local;
        this.theme = theme;
    }

    public static CurationResponseDto fromEntity(Curation curation) {
        return CurationResponseDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .build();
    }

    public void changeFileList(List<Long> fileList){
        this.fileList = fileList;
    }
}