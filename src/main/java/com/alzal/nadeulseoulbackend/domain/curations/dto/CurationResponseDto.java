package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CurationResponseDto {
    private Long curationSeq;
    private UserInfoDto userinfos;
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
    private List<StoreInCurationDto> curationCourse;
    private String transportation;

    @Builder
    public CurationResponseDto(Long curationSeq, Long memberSeq, UserInfoDto userinfos, String title,
                               String description, Integer personnel, Integer budget, LocalDateTime date,
                               Integer good, Integer views, Integer photoCount, List<Long> fileList, List<CodeDto> local,
                               List<CodeDto> theme, List<StoreInCurationDto> curationCourse, String transportation) {
        this.curationSeq = curationSeq;
        this.userinfos = userinfos;
        this.title = title;
        this.description = description;
        this.personnel = personnel;
        this.budget = budget;
        this.date = date;
        this.good = good;
        this.views = views;
        this.photoCount = photoCount;
        this.fileList = fileList;
        this.local = local;
        this.theme = theme;
        this.curationCourse = curationCourse;
        this.transportation = transportation;
    }

    public static CurationResponseDto fromEntity(Curation curation) {
        return CurationResponseDto.builder()
                .curationSeq(curation.getCurationSeq())
                .title(curation.getTitle())
                .build();
    }

    public void changeFileList(List<Long> fileList) {
        this.fileList = fileList;
    }
}