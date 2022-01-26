package com.alzal.nadeulseoulbackend.domain.tag.dto;

import com.alzal.nadeulseoulbackend.domain.tag.entity.ThemeTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThemeTagDto {

    private String tagName;

    @Builder
    public ThemeTagDto(String tagName) {
        this.tagName = tagName;
    }

    public ThemeTag toEntity() {
        return ThemeTag.builder()
                .tagName(tagName)
                .build();
    }

    public static ThemeTagDto fromEntity(ThemeTag themeTag) {
        return ThemeTagDto.builder()
                .tagName(themeTag.getTagName())
                .build();
    }


}
