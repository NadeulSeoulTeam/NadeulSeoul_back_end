package com.alzal.nadeulseoulbackend.domain.stores.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private String x;
    private String y;

    @ColumnDefault("0")
    private Long bookmarkCount;

    @Builder
    public StoreInfo(Long storeSeq, String storeName, String categoryName, String addressName,
                     String placeUrl, String phone, String x, String y, Long bookmarkCount) {
        this.storeSeq = storeSeq;
        this.storeName = storeName;
        this.categoryName = categoryName;
        this.addressName = addressName;
        this.placeUrl = placeUrl;
        this.phone = phone;
        this.x = x;
        this.y = y;
        this.bookmarkCount = bookmarkCount;
    }

    public void updateBookmarkCount() {
        this.bookmarkCount++;
    }

    public void deleteBookmarkCount() {
        this.bookmarkCount--;
    }
}
