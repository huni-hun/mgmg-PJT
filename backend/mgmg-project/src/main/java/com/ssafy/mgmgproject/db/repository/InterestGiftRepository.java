package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.InterestGift;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestGiftRepository extends JpaRepository<InterestGift, Long> {
    List<InterestGift> findByUser(User user);
}

