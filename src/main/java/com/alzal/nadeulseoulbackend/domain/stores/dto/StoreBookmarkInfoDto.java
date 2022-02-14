package com.alzal.nadeulseoulbackend.domain.stores.dto;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreBookmarkInfoDto {
    private Long storeSeq;
    private String storeName;
    private String addressName;
    private String categoryName;

    static public StoreBookmarkInfoDto fromEntity(StoreInfo storeInfo) {
        return StoreBookmarkInfoDto.builder()
                .storeSeq(storeInfo.getStoreSeq())
                .storeName(storeInfo.getStoreName())
                .categoryName(storeInfo.getCategoryName())
                .build();
    }
}
