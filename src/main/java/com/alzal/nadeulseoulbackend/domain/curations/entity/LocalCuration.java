package com.alzal.nadeulseoulbackend.domain.curations.entity;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_local_in_curation")
public class LocalCuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long seq;

    @ManyToOne
    @JoinColumn(name = "curation_seq")
    Curation curation;

    @ManyToOne
    @JoinColumn(name = "code_seq")
    Code code;

    @Builder
    public LocalCuration(Curation curation, Code code) {
        this.curation = curation;
        this.code = code;
    }
}
