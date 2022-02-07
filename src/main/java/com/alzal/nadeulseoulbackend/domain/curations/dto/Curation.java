package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_curation")
public class Curation {

    @Id
    private Long curationSeq;
    private String title;
    private Integer budget;
    private Integer personnel;
    private String desc;
    private LocalDateTime date;
    private Integer good;
    private Integer views;
    private Long memberSeq;
    private Integer photoCount;
    private Boolean hidden;

    @OneToMany(mappedBy = "curation")
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Curation(Long curationSeq, String title, Integer budget, Integer personnel,
                    String desc, LocalDateTime date, Integer good, Integer views,
                    Long memberSeq, Integer photoCount, Boolean hidden) {
        this.curationSeq = curationSeq;
        this.title = title;
        this.budget = budget;
        this.personnel = personnel;
        this.desc = desc;
        this.date = date;
        this.good = good;
        this.views = views;
        this.memberSeq = memberSeq;
        this.photoCount = photoCount;
        this.hidden = hidden;
    }
}
