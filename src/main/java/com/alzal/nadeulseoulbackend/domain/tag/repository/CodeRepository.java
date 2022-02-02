package com.alzal.nadeulseoulbackend.domain.tag.repository;

import com.alzal.nadeulseoulbackend.domain.tag.dto.Code;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, Long> {
}
