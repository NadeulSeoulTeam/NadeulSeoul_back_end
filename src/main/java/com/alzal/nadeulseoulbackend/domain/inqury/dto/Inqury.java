package com.alzal.nadeulseoulbackend.domain.inqury.dto;

import com.sun.istack.NotNull;
import lombok.*;

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
    @GeneratedValue
    private Long questionSeq;

    private Long memberSeq; // 회원 아이디 foreign key 설정해야함

    @Column(nullable = false)
    private String questionTitle;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    private LocalDateTime questionDate;

    private String answer;

    private LocalDateTime answerDate;

    private boolean hidden = false;

}
