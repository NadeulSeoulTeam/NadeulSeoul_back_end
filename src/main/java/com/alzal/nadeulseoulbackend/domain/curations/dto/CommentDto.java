package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDto {

    private Long commentSeq;
    private Long curationSeq;
    private Long memberSeq;
    private String content;
    private Curation curation;

    @Builder
    public CommentDto(Long curationSeq, Long memberSeq, String content) {
        this.curationSeq = curationSeq;
        this.memberSeq = memberSeq;
        this.content = content;
    }

    @Builder
    public CommentDto(Long commentSeq, Long curationSeq, Long memberSeq, String content) {
        this.commentSeq = commentSeq;
        this.curationSeq = curationSeq;
        this.memberSeq = memberSeq;
        this.content = content;
    }

    public Comment toEntity() {
        return Comment.builder()
                .commentSeq(commentSeq)
                .curation(curation)
                .memberSeq(memberSeq)
                .content(content)
                .build();
    }

    public static CommentDto fromEntity(Comment comment) {
        return CommentDto.builder()
                .curationSeq(comment.getCurationSeq())
                .memberSeq(comment.getMemberSeq())
                .content(comment.getContent())
                .build();
    }
}
