package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CurationRequestDto {
    private Long curationSeq;
    private String title;
    private String description;
    private Integer personnel;
    private Integer budget;
    private LocalDateTime date;
    private Integer good;
    private Integer views;
    private Integer photoCount;
    private List<MultipartFile> fileList;
    private List<Long> theme;
    private List<Long> local;

    @Builder
    public CurationRequestDto(Long curationSeq, String title, String description,
                              Integer personnel, Integer budget, LocalDateTime date, Integer good,
                              Integer views, Integer photoCount, List<MultipartFile> fileList,
                              List<Long> theme, List<Long> local
    ) {
        this.curationSeq = curationSeq;
        this.title = title;
        this.description = description;
        this.personnel = personnel;
        this.budget = budget;
        this.date = date;
        this.good = good;
        this.views = views;
        this.photoCount = photoCount;
        this.fileList = fileList;
        this.theme = theme;
        this.local = local;
    }
}
