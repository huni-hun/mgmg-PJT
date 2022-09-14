package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.User;

public interface UserService {
    User getByUserId(String userId);
}
