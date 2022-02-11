package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurationRepository extends JpaRepository<Curation, Long> {
    List<Curation> findTop10ByHiddenFalseOrderByViewsDesc();
}
