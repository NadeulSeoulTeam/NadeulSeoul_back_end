package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CurationDto {
    private Long curationSeq;
    private String title;
    private Integer budget;
    private Integer personnel;
    private String description;
    private LocalDateTime date;
    private Integer good;
    private Integer views;
    private Long memberSeq;
    private Integer photoCount;
    private List<ImageDto> fileList;
//    private List<MultipartFile> fileList;
}
