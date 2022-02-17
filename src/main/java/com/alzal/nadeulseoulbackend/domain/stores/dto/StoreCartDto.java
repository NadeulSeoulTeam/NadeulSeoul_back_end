package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class StoreCartDto {
    private Long userSeq;
    private List<Long> storeSeqList;
}
