package com.alzal.nadeulseoulbackend.domain.stores.repository;

import com.alzal.nadeulseoulbackend.domain.stores.entity.MemberStoreId;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<StoreInfo, MemberStoreId> {

}
