package com.alzal.nadeulseoulbackend.domain.stores.repository;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
    List<StoreInfo> findTop10ByOrderByBookmarkCountDesc();
}
