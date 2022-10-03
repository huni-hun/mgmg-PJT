package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.api.response.NotificationListMapping;
import com.ssafy.mgmgproject.db.entity.Notification;
import com.ssafy.mgmgproject.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    long countByUser(User user);

    Optional<List<NotificationListMapping>> findByUserOrderByNotificationNoDesc(User user);

    void deleteByUser(User user);

}
