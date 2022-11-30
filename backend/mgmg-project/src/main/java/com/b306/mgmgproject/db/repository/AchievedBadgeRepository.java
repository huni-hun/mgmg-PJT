package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.AchievedBadge;
import com.b306.mgmgproject.db.entity.Badge;
import com.b306.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AchievedBadgeRepository extends JpaRepository<AchievedBadge, Long> {

    List<AchievedBadge> findByUserOrderByBadgeAsc(User user);

    Optional<AchievedBadge> findByUserAndBadge(User user, Badge badge);
    
}
