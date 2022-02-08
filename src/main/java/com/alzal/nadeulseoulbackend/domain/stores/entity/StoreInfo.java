package com.alzal.nadeulseoulbackend.domain.stores.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "tb_store_info")
public class StoreInfo {

    @Id
    private Long storeSeq; //카카오 api 상가id
    private String placeName;
    private String categoryName;
    private String addressName;
    private String placeUrl;
    private String phone;
    private String lat;
    private String lng;

    @Builder
    public StoreInfo(Long storeSeq, String placeName, String categoryName, String addressName,
                     String placeUrl, String phone, String lat, String lng) {
        this.storeSeq = storeSeq;
        this.placeName = placeName;
        this.categoryName = categoryName;
        this.addressName = addressName;
        this.placeUrl = placeUrl;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
    }
}
