package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_curation")
@SequenceGenerator(
        name = "CURATION_SEQ_GENERATOR",
        sequenceName = "CURATIOIN_SEQ",
        initialValue = 1, // 초기값
        allocationSize = 1 // 미리 할당 받은 시퀀스 수
)
public class Curation {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long curationSeq;
    private String title;
    private Integer budget;
    private Integer personnel;
    private String description;

    @UpdateTimestamp
    private LocalDateTime date;

    private Integer good;
    private Integer views;
    private Long memberSeq;
    private Integer photoCount;

    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean hidden;

    @OneToMany(mappedBy = "curation")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "curation")
    private List<Image> imageList = new ArrayList<>();

    @Builder
    public Curation(Long curationSeq, String title, Integer budget, Integer personnel,
                    String description, LocalDateTime date, Integer good, Integer views,
                    Long memberSeq, Integer photoCount, Boolean hidden) {
        this.curationSeq = curationSeq;
        this.title = title;
        this.budget = budget;
        this.personnel = personnel;
        this.description = description;
        this.date = date;
        this.good = good;
        this.views = views;
        this.memberSeq = memberSeq;
        this.photoCount = photoCount;
        this.hidden = hidden;
    }

    public void changeHidden(Boolean flag) {
        this.hidden = flag;
    }
    public void addImage(Image image) {
        this.imageList.add(image);
    }
//    public void changePhotoCount(Integer photoCount) {
//        this.photoCount = photoCount;
//    }
}
