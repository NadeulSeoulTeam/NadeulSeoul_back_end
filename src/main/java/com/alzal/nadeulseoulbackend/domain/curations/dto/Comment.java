package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_comment")
@SequenceGenerator(
        name = "COMMENT_SEQ_GENERATOR",
        sequenceName = "COMMENT_SEQ",
        initialValue = 1, // 초기값
        allocationSize = 1 // 미리 할당 받은 시퀀스 수
)
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long commentSeq;

    @Column(name = "curation_seq", insertable = false, updatable = false)
    private Long curationSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_seq")
    private Curation curation;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;

    private String content;

    @UpdateTimestamp
    private LocalDateTime date;

    @Builder
    public Comment(Long commentSeq, Curation curation, User user, String content, LocalDateTime date) {
        this.commentSeq = commentSeq;
        this.curation = curation;
        this.user = user;
        this.content = content;
        this.date = date;
    }

    public void change(String content) {
        this.content = content;
    }
}
