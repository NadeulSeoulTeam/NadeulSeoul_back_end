package com.alzal.nadeulseoulbackend.domain.stores.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BookmarkDto {
    private Long BookmarkCount;
    private boolean IsBookmark;
}
