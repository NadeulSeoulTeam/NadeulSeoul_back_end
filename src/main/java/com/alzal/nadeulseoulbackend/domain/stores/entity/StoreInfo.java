package com.alzal.nadeulseoulbackend.domain.stores.entity;

import lombok.Builder;
import lombok.Data;
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
    private String storeName;
    private String categoryName;
    private String addressName;
    private String placeUrl;
    private String phone;
    private String lat;
    private String lng;
    private Long bookmarkCount;

    @Builder
    public StoreInfo(Long storeSeq, String storeName, String categoryName, String addressName,
                     String placeUrl, String phone, String lat, String lng, Long bookmarkCount) {
        this.storeSeq = storeSeq;
        this.storeName = storeName;
        this.categoryName = categoryName;
        this.addressName = addressName;
        this.placeUrl = placeUrl;
        this.phone = phone;
        this.lat = lat;
        this.lng = lng;
        this.bookmarkCount = bookmarkCount;
    }

    public void updateBookmarkCount() {
        this.bookmarkCount++;
    }

    public void deleteBookmarkCount() {
        this.bookmarkCount--;
    }
}
