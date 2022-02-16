package com.alzal.nadeulseoulbackend.domain.users.repository;

import com.alzal.nadeulseoulbackend.domain.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    Optional<User> findById(Long userSeq);
    boolean existsByNickname(String nickname);

    List<User> findTop10ByOrderByMyCurationCountDesc();
}
