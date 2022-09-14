package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByUserIdAndEmail(String userId, String email);

    Optional<User> findByUserNameAndEmail(String userName, String email);

    Optional<User> findByEmail(String email);
}