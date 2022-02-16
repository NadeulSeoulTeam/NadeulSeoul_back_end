package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByCurationSeq(Long curationSeq, Pageable pageable);
}
