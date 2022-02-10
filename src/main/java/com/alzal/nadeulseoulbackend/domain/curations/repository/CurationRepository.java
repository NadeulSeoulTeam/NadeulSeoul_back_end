package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurationRepository extends JpaRepository<Curation, Long> {
    List<Curation> findTop10ByOrderByViewsDesc();
//    List<Curation> findAll();
}
