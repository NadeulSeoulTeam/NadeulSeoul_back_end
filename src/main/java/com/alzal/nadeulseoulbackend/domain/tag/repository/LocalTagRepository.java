package com.alzal.nadeulseoulbackend.domain.tag.repository;

import com.alzal.nadeulseoulbackend.domain.tag.entity.LocalTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalTagRepository extends JpaRepository<LocalTag, Integer> {
}
