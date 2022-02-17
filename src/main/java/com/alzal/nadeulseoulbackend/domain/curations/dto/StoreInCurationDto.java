package com.alzal.nadeulseoulbackend.domain.curations.dto;

import com.alzal.nadeulseoulbackend.domain.curations.entity.StoreInCuration;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StoreInCurationDto {
    private Integer storeOrder;
    private StoreInfoDto storeInfoDto;

    @Builder
    public StoreInCurationDto(Integer storeOrder, StoreInfoDto storeInfoDto) {
        this.storeOrder = storeOrder;
        this.storeInfoDto = storeInfoDto;
    }


    public static StoreInCurationDto fromEntity(StoreInCuration storeInCuration){
        return StoreInCurationDto.builder()
                .storeInfoDto(StoreInfoDto.fromEntity(storeInCuration.getStoreInfo()))
                .storeOrder(storeInCuration.getStoreOrder())
                .build();
    }

}
