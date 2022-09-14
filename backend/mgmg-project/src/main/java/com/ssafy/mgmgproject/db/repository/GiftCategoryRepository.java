package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.GiftCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCategoryRepository extends JpaRepository<GiftCategory, Long> {
}
