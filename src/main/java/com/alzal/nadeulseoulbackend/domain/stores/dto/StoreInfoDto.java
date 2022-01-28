package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreInfoDto {

    private Long sotreSeq;
    private String placeName;
    private String categoryName;
    private String addressName;
    private String placeUrl;
    private String phone;
    private String y;
    private String x;
    
    @Builder
    public StoreInfoDto(Long sotreSeq, String placeName, String categoryName,
                        String addressName, String placeUrl, String phone,
                        String y, String x) {
        this.sotreSeq = sotreSeq;
        this.placeName = placeName;
        this.categoryName = categoryName;
        this.addressName = addressName;
        this.placeUrl = placeUrl;
        this.phone = phone;
        this.y = y;
        this.x = x;
    }

    public StoreInfo toEntyty(){
        return StoreInfo.builder()
                .sotreSeq(sotreSeq)
                .placeName(placeName)
                .categoryName(categoryName)
                .placeUrl(placeUrl)
                .phone(phone)
                .lat(y)
                .lng(x)
                .build();
    }

    public StoreInfoDto fromEntity(StoreInfo storeInfo) {
        return StoreInfoDto.builder()
                .sotreSeq(storeInfo.getSotreSeq())
                .placeName(storeInfo.getPlaceName())
                .categoryName(storeInfo.getCategoryName())
                .placeUrl(storeInfo.getPlaceUrl())
                .phone(storeInfo.getPhone())
                .y(storeInfo.getLat())
                .x(storeInfo.getLng())
                .build();
    }
}
