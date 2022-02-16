package com.alzal.nadeulseoulbackend.domain.inquiry.entity;

import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "tb_inquiry")
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionSeq;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user; // 회원 아이디 foreign key 설정해야함

    @Column(nullable = false)
    private String questionTitle;

    @Column(nullable = false)
    private String question;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime questionDate;

    private String answer;

    @UpdateTimestamp
    private LocalDateTime answerDate;

    @Column(columnDefinition = "boolean default false")
    private boolean hidden;

    public void update(String questionTitle, String question) {
        this.questionTitle = questionTitle;
        this.question = question;
    }

    public void setHidden(boolean hidden) {
        this.hidden = true;
    }

    public void updateAnswer(String answer) {
        this.answer = answer;
    }
}
