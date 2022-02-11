package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_theme_in_curation")
public class ThemeCuration {

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
    public ThemeCuration(Curation curation, Code code) {
        this.curation = curation;
        this.code = code;
    }
}
