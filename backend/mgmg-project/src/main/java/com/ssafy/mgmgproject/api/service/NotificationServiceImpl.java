package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.response.NotificationListMapping;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService{

    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public boolean checkNewNotification(User user){
        return 0<notificationRepository.countByUser(user);
    }

    @Override
    @Transactional
    public List<NotificationListMapping> selectAndDeleteNotificationList(User user) {
        List<NotificationListMapping> notifications = notificationRepository.findByUserOrderByNotificationNoDesc(user).orElse(null);
        notificationRepository.deleteByUser(user);
        return notifications;
    }

}
