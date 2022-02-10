package com.alzal.nadeulseoulbackend.domain.curations.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_curation_image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long imageSeq;
    private String originName;
    private Integer imageOrder;
    private String imagePath;
    private Long imageSize;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curation_seq")
    private Curation curation;

    @Builder
    public Image(String originName, Integer imageOrder,
                 String imagePath, Long imageSize, Curation curation) {
        this.originName = originName;
        this.imageOrder = imageOrder;
        this.imagePath = imagePath;
        this.imageSize = imageSize;
        this.curation = curation;
    }
}
