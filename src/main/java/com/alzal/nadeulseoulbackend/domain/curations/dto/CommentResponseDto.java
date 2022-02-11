package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long commentSeq;
    private Long curationSeq;
    private String content;
    private UserCommentDto user;
    private LocalDateTime date;

    @Builder
    public CommentResponseDto(Long commentSeq, Long curationSeq, UserCommentDto user, String content, LocalDateTime date) {
        this.commentSeq = commentSeq;
        this.curationSeq = curationSeq;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .curationSeq(comment.getCurationSeq())
                .user(UserCommentDto.fromEntity(comment.getUser()))
                .content(comment.getContent())
                .date(comment.getDate())
                .build();
    }
}
