package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.dto.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.dto.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepositoroy extends JpaRepository<Image, Long> {
    List<Image> findAllByCuration(Curation curation);
}
