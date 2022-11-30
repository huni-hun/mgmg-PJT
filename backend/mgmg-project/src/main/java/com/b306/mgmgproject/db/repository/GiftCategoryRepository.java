package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.GiftCategory;
import com.b306.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GiftCategoryRepository extends JpaRepository<GiftCategory, Long> {

    List<GiftCategory> findByUser(User user);

    void deleteByUser(User user);

}
