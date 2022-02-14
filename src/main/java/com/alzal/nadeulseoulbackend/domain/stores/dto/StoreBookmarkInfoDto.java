package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StoreBookmarkInfoDto {
    private Long storeSeq;
    private String storeName;
    private String addressName;
    private String categoryName;
}
