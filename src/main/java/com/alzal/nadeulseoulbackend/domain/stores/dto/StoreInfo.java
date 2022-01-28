package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table
public class StoreInfo {

    @Id
    private Long sotreSeq;
    private String placeName;
    private String categoryName;
    private String addressName;
    private String placeUrl;
    private String phone;
    private String lat;
    private String lng;

    @Builder
    public StoreInfo(Long sotreSeq, String placeName, String categoryName, String addressName,
                     String placeUrl, String phone, String lat, String lng) {
        this.sotreSeq = sotreSeq;
        this.placeName = placeName;
        this.categoryName = categoryName;
        this.addressName = addressName;
        this.placeUrl = placeUrl;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
    }
}
