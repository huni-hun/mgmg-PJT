package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.Notification;
import com.b306.mgmgproject.db.entity.User;
import com.b306.mgmgproject.api.response.NotificationListMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    long countByUser(User user);

    Optional<List<NotificationListMapping>> findByUserOrderByNotificationNoDesc(User user);

    void deleteByUser(User user);

}
