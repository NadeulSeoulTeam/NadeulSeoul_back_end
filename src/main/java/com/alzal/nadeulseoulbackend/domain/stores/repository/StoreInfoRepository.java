package com.alzal.nadeulseoulbackend.domain.stores.repository;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
    // 장소 상세 정보 가져오기
    Optional<StoreInfo> findByStoreSeq(Long storeSeq);
}
