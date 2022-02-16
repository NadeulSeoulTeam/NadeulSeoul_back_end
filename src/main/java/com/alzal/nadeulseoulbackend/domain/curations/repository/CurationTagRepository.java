package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CurationTagRepository extends JpaRepository<Curation, Long>, CurationTagRepositoryCustom {
    List<Curation> findTop10ByHiddenFalseOrderByViewsDesc();

    Page<Curation> findByUserSeqAndHiddenIsFalse(Long userSeq, Pageable pageable);
    
}
