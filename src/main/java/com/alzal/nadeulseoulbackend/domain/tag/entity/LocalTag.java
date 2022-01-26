package com.alzal.nadeulseoulbackend.domain.tag.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_local_tag")
public class LocalTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer localNo;

    private String localName;
    private String localLat;
    private String localLng;

    @Builder
    public LocalTag(String localName, String localLat, String localLng){
        this.localName = localName;
        this.localLat = localLat;
        this.localLng = localLng;
    }
}
