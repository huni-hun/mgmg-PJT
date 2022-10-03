package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {

    Optional<Badge> findByBadgeConditionStartingWithAndBadgeConditionContaining(String startKeyword, String keyword);

}
