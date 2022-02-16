package com.alzal.nadeulseoulbackend.domain.stores.repository;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
    // 찜 목록 가져오기 pagination
    Page<StoreInfo> findAll(Pageable pageable);

    List<StoreInfo> findTop10ByOrderByBookmarkCountDesc();
}
