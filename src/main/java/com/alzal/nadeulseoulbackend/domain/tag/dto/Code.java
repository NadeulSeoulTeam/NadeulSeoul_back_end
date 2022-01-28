package com.alzal.nadeulseoulbackend.domain.tag.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_code")
public class Code {

    @Id
    private Long codeSeq;

    private String codeName;

    @ManyToOne
    @JoinColumn(name="group_seq")
    private CodeGroup codeGroup;

    @Builder
    public Code(Long codeSeq, String codeName, CodeGroup codeGroup) {
        this.codeSeq = codeSeq;
        this.codeName = codeName;
        this.codeGroup = codeGroup;
    }
}
