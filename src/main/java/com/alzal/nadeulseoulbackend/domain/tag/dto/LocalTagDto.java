package com.alzal.nadeulseoulbackend.domain.tag.dto;

import com.alzal.nadeulseoulbackend.domain.tag.entity.LocalTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LocalTagDto {

    private String localName;
    private String localLat;
    private String localLng;

    @Builder
    public LocalTagDto(String localName, String localLat, String localLng){
        this.localName = localName;
        this.localLat = localLat;
        this.localLng = localLng;
    }

    public LocalTag toEntity() {
        return LocalTag.builder()
                .localName(localName)
                .localLat(localLat)
                .localLng(localLng)
                .build();
    }

    public static LocalTagDto fromEntity(LocalTag localTag) {
        return LocalTagDto.builder()
                .localName(localTag.getLocalName())
                .localLat(localTag.getLocalLat())
                .localLng(localTag.getLocalLng())
                .build();
    }
}
