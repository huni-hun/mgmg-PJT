package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.UserRegistPostRequest;
import com.ssafy.mgmgproject.api.request.UserUpatePutRequest;
import com.ssafy.mgmgproject.db.entity.User;

public interface UserService {
    User getByUserId(String userId);

    void createUser(UserRegistPostRequest userRegistInfo);

    User getByUserIdAndEmail(String userId, String email);

    User getByUserNameAndEmail(String userName, String email);

    User getByEmail(String email);

    int updateUser(User user, UserUpatePutRequest userUpatePutRequest);

    void updatePassword(User user, String newPassword);
}
