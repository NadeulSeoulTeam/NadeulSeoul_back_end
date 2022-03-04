package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class BookmarkDto {
    private Long BookmarkCount;
    private boolean IsBookmark;
}
