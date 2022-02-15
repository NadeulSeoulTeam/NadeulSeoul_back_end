package com.alzal.nadeulseoulbackend.domain.tag.repository;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeRepository extends JpaRepository<Code, Long> {
}