package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageRequestDto {
    private int page;
    private int size;
}
