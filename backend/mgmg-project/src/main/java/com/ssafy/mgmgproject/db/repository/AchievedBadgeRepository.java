package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.AchievedBadge;
import com.ssafy.mgmgproject.db.entity.Badge;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievedBadgeRepository extends JpaRepository<AchievedBadge, Long> {
    List<AchievedBadge> findByUser(User user);
    Optional<AchievedBadge> findByUserAndBadge(User user, Badge badge);
}
