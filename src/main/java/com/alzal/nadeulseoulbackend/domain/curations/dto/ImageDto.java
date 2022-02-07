package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto {
    private Integer imageOrder;
    private MultipartFile multipartFile;

    @Builder
    public ImageDto(Integer imageOrder, MultipartFile multipartFile) {
        this.imageOrder = imageOrder;
        this.multipartFile = multipartFile;
    }
}
