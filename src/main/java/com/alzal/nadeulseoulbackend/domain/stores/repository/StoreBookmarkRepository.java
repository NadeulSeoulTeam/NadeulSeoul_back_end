package com.alzal.nadeulseoulbackend.domain.stores.repository;

import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreBookmark;
import com.alzal.nadeulseoulbackend.domain.stores.entity.StoreInfo;
import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreBookmarkRepository extends JpaRepository<StoreBookmark, Long> {
    Optional<StoreBookmark> findByUserAndStoreInfo(User user, StoreInfo storeInfo);
}
