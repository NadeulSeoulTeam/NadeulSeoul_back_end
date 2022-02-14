package com.alzal.nadeulseoulbackend.domain.stores.repository;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreInfoRepository extends JpaRepository<StoreInfo, Long> {
}
