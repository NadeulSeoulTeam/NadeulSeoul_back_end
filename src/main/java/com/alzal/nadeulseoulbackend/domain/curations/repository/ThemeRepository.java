package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.dto.ThemeCuration;
import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThemeRepository extends JpaRepository<ThemeCuration, Long> {
    void deleteByCuration(Curation curation);
}
