package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.GiftCategory;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftCategoryRepository extends JpaRepository<GiftCategory, Long> {
    List<GiftCategory> findByUser(User user);
}
