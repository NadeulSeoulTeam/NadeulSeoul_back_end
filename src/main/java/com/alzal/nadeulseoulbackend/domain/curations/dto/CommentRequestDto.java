package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {

    private Long commentSeq;
    private Long curationSeq;
    private String content;

    @Builder
    public CommentRequestDto(Long curationSeq, String content) {
        this.curationSeq = curationSeq;
        this.content = content;
    }

    @Builder
    public CommentRequestDto(Long commentSeq, Long curationSeq, String content) {
        this.commentSeq = commentSeq;
        this.curationSeq = curationSeq;
        this.content = content;
    }

}
