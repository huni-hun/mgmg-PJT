package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.db.entity.User;
import com.b306.mgmgproject.api.response.NotificationListMapping;

import java.util.List;

public interface NotificationService {

    boolean checkNewNotification(User user);

    List<NotificationListMapping> selectAndDeleteNotificationList(User user);

}
