package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.User;

public interface NotificationService {

    boolean checkNewNotification(User user);

}
