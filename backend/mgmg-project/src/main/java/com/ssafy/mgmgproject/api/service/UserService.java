package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.UserRegistPostRequest;
import com.ssafy.mgmgproject.db.entity.User;

public interface UserService {
    User getByUserId(String userId);

    void createUser(UserRegistPostRequest userRegistInfo);
}
