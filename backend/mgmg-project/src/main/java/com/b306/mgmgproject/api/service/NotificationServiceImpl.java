package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.db.entity.User;
import com.b306.mgmgproject.db.repository.NotificationRepository;
import com.b306.mgmgproject.api.response.NotificationListMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public boolean checkNewNotification(User user) {
        return 0 < notificationRepository.countByUser(user);
    }

    @Override
    @Transactional
    public List<NotificationListMapping> selectAndDeleteNotificationList(User user) {
        List<NotificationListMapping> notifications = notificationRepository.findByUserOrderByNotificationNoDesc(user).orElse(null);
        notificationRepository.deleteByUser(user);
        return notifications;
    }

}
