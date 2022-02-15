package com.alzal.nadeulseoulbackend.domain.tag.dto;

import lombok.Data;

import java.util.List;

@Data
public class CodeRequestDto {
    private List<Long> theme;
    private List<Long> local;
}
