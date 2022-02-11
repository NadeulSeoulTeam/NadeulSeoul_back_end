package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ImageDto {

    private String originName;
    private String imagePath;
    private Long imageSize;

    @Builder
    public ImageDto(String origFileName, String imagePath, Long imageSize) {
        this.originName = origFileName;
        this.imagePath = imagePath;
        this.imageSize = imageSize;
    }

    public Image toEntity() {
        return Image.builder()
                .originName(originName)
                .imagePath(imagePath)
                .imageSize(imageSize)
                .build();
    }
}
