package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CurationTagRepositoryCustom {
    Page<Curation> searchByTag(CodeRequestDto codeRequestDto, Pageable pageable);
}
