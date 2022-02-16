package com.alzal.nadeulseoulbackend.domain.stores.dto;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreInfoDto {

    private Long storeSeq;
    private String storeName;
    private String categoryName;
    private String addressName;
    private String placeUrl;
    private String phone;
    private String x;
    private String y;

    @Builder
    public StoreInfoDto(Long storeSeq, String storeName, String categoryName,
                        String addressName, String placeUrl, String phone,
                        String x, String y) {
        this.storeSeq = storeSeq;
        this.storeName = storeName;
        this.categoryName = categoryName;
        this.addressName = addressName;
        this.placeUrl = placeUrl;
        this.phone = phone;
        this.x = x;
        this.y = y;
    }

    public StoreInfo toEntity() {
        return StoreInfo.builder()
                .storeSeq(storeSeq)
                .storeName(storeName)
                .categoryName(categoryName)
                .placeUrl(placeUrl)
                .phone(phone)
                .x(x)
                .y(y)
                .build();
    }

    static public StoreInfoDto fromEntity(StoreInfo storeInfo) {
        return StoreInfoDto.builder()
                .storeSeq(storeInfo.getStoreSeq())
                .storeName(storeInfo.getStoreName())
                .categoryName(storeInfo.getCategoryName())
                .placeUrl(storeInfo.getPlaceUrl())
                .phone(storeInfo.getPhone())
                .x(storeInfo.getX())
                .x(storeInfo.getY())
                .build();
    }
}
