package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.Notification;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NotificationRepository extends JpaRepository<Notification, Long> {
    long countByUser(User user);
}
