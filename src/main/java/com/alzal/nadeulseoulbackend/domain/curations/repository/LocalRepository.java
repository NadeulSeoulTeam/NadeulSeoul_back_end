package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.dto.LocalCuration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface LocalRepository extends JpaRepository<LocalCuration, Long> {
    void deleteByCuration(Curation curation);
}
