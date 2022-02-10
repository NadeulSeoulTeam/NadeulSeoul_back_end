package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

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

    @Builder
    public CurationResponseDto(Long curationSeq, Long memberSeq, String title, String description,
                               Integer personnel, Integer budget, LocalDateTime date, Integer good,
                               Integer views, Integer photoCount, List<Long> fileList) {
        this.curationSeq = curationSeq;
        this.memberSeq = memberSeq;
        this.title = title;
        this.description = description;
        this.personnel = personnel;
        this.budget = budget;
        this.date = date;
        this.good = good;
        this.views = views;
        this.photoCount = photoCount;
        this.fileList = fileList;
    }
}
