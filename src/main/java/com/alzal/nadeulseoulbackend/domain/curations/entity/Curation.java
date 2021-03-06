package com.alzal.nadeulseoulbackend.domain.curations.entity;

import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

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
    private Integer photoCount;
    @ColumnDefault("0")
    private Long thumnail;
    @Column(nullable = false, columnDefinition = "TINYINT(1)")
    private Boolean hidden;
    private String transportation;

    @ManyToOne
    @JoinColumn(name = "user_seq")
    private User user;
    @Column(name = "user_seq", insertable = false, updatable = false)
    private Long userSeq;
    @BatchSize(size = 100)
    @OneToMany(mappedBy = "curation")
    private Set<Comment> commentList = new LinkedHashSet<>();
    @OneToMany(mappedBy = "curation")
    private List<Image> imageList = new ArrayList<>();

    @OneToMany(mappedBy = "curation")
    Set<LocalCuration> localCuration;

    @OneToMany(mappedBy = "curation")
    Set<ThemeCuration> themeCuration;

    @OneToMany(mappedBy = "curation")
    List<StoreInCuration> storeInCuration;

    @Builder
    public Curation(Long curationSeq, String title, Integer budget, Integer personnel,
                    String description, LocalDateTime date, Integer good, Integer views,
                    Integer photoCount, Long thumnail, Boolean hidden, User user, String transportation) {
        this.curationSeq = curationSeq;
        this.title = title;
        this.budget = budget;
        this.personnel = personnel;
        this.description = description;
        this.date = date;
        this.good = good;
        this.views = views;
        this.photoCount = photoCount;
        this.thumnail = thumnail;
        this.hidden = hidden;
        this.user = user;
        this.transportation = transportation;
    }

    public void changeHidden(Boolean flag) {
        this.hidden = flag;
    }

    public void addImage(Image image) {
        this.imageList.add(image);
    }

    public void changeThumnail(Long imageSeq) {
        this.thumnail = imageSeq;
    }

    public void changeCuration(String title, Integer budget, Integer personnel,
                               String description, Integer photoCount) {
        this.title = title;
        this.budget = budget;
        this.personnel = personnel;
        this.description = description;
        this.photoCount = photoCount;
    }

    public void getCourseInfo(){
         }
    public void addLocalTag(LocalCuration localCuration) {
        this.localCuration.add(localCuration);
    }

    public void addThemeTag(ThemeCuration themeCuration) {
        this.themeCuration.add(themeCuration);
    }

    public void addViews() {
        this.views++;
    }

    public void addGood() {
        this.good++;
    }

    public void removeGood() {
        this.good++;
    }
}
