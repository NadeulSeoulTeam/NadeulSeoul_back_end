package com.alzal.nadeulseoulbackend.domain.inqury.dto;

import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Builder
@Table(name = "tb_inqury")
public class Inqury {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long questionSeq;

    private Long memberSeq; // 회원 아이디 foreign key 설정해야함

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

    private boolean hidden = false;

    public void update(String questionTitle, String question){
        this.questionTitle = questionTitle;
        this.question = question;
    }

    public void setHidden(boolean hidden){
        this.hidden = true;
    }

    public void updateAnswer(String answer) {
        this.answer = answer;
    }
}
