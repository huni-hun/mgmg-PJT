package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.Gift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftRepository extends JpaRepository<Gift, Long> {

    Optional<Gift> findByGiftNo(Long giftNo);

}

