package com.alzal.nadeulseoulbackend.domain.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CodeDto {

    private Long codeSeq;
    private String codeName;

    @Builder
    public CodeDto(Long codeSeq, String codeName) {
        this.codeSeq = codeSeq;
        this.codeName = codeName;
    }

    public static CodeDto fromEntity(Code code) {
        return CodeDto.builder()
                .codeSeq(code.getCodeSeq())
                .codeName(code.getCodeName())
                .build();
    }

    public Code toEntity() {
        return Code.builder()
                .codeName(codeName)
                .build();
    }
}
