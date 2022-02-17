package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Comment;
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
    private UserInfoDto user;
    private LocalDateTime date;

    @Builder
    public CommentResponseDto(Long commentSeq, Long curationSeq, UserInfoDto user, String content, LocalDateTime date) {
        this.commentSeq = commentSeq;
        this.curationSeq = curationSeq;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public static CommentResponseDto fromEntity(Comment comment) {
        return CommentResponseDto.builder()
                .commentSeq(comment.getCommentSeq())
                .curationSeq(comment.getCurationSeq())
                .user(UserInfoDto.fromEntity(comment.getUser()))
                .content(comment.getContent())
                .date(comment.getDate())
                .build();
    }
}
