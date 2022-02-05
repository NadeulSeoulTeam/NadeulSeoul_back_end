package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name="curation_seq", insertable = false, updatable = false)
    private Long curationSeq;

    @ManyToOne( fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_seq")
    private Curation curation;

    private Long memberSeq;
    private String content;

    @UpdateTimestamp
    private LocalDateTime date;

    @Builder
    public Comment(Long commentSeq, Curation curation, Long memberSeq, String content, LocalDateTime date) {
        this.commentSeq = commentSeq;
        this.curation = curation;
        this.memberSeq = memberSeq;
        this.content = content;
        this.date = date;
    }

    public void change(String content) {
        this.content = content;
    }
}
