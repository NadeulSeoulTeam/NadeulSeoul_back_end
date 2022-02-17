package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class StoreCartDto {
    private Long userSeq;
    private List<Long> storeSeqList;
}
