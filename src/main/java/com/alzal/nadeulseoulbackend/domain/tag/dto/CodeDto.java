package com.alzal.nadeulseoulbackend.domain.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CodeDto {

    private String codeName;

    @Builder
    public CodeDto(String codeName) {
        this.codeName = codeName;
    }

    public Code toEntity() {
        return Code.builder()
                .codeName(codeName)
                .build();
    }

    public static CodeDto fromEntity(Code code) {
        return CodeDto.builder()
                .codeName(code.getCodeName())
                .build();
    }
}
