package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.InterestGift;
import com.b306.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterestGiftRepository extends JpaRepository<InterestGift, Long> {

    List<InterestGift> findByUser(User user);

    void deleteByUserAndGift_GiftNo(User user, long giftNo);

    Optional<InterestGift> findByUser_UserNoAndGift_GiftNo(long userNo, long giftNo);

}

