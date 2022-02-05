package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurationRepository extends JpaRepository<Curation, Long> {
}
