package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreInfoDto;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
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
    private List<Long> theme;
    private List<Long> local;
    private List<StoreInfoDto> courseRoute;
    private String transportation;

    @Builder
    public CurationRequestDto(Long curationSeq, String title, String description,
                              Integer personnel, Integer budget, LocalDateTime date, Integer good,
                              Integer views, Integer photoCount, List<Long> theme, List<Long> local,
                              List<StoreInfoDto>courseRoute, String transportation
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
        this.courseRoute = courseRoute;
        this.theme = theme;
        this.local = local;
        this.transportation = transportation;
    }
}
