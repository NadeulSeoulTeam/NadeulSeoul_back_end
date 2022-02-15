package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.entity.CurationBookmark;
import com.alzal.nadeulseoulbackend.domain.mypage.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurationBookmarkRepository extends JpaRepository<CurationBookmark, Long> {
    Optional<CurationBookmark> findByUserAndCuration(User user, Curation curation);

    // 찜한 큐레이션 리스트 pagination
    Page<CurationBookmark> findByUser(Pageable pageable, User user);
}
