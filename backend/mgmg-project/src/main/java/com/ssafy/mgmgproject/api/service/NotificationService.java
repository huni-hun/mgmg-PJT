package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.response.NotificationListMapping;
import com.ssafy.mgmgproject.db.entity.User;

import java.util.List;

public interface NotificationService {

    boolean checkNewNotification(User user);
    List<NotificationListMapping> selectAndDeleteNotificationList(User user);
}
