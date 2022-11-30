package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.api.request.UserChangeGiftPutRequest;
import com.b306.mgmgproject.api.request.UserChangeMusicPutRequest;
import com.b306.mgmgproject.api.request.UserRegistPostRequest;
import com.b306.mgmgproject.api.request.UserUpdatePutRequest;
import com.b306.mgmgproject.db.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getByUserId(String userId);

    void createUser(UserRegistPostRequest userRegistInfo);

    User getByUserIdAndEmail(String userId, String email);

    User getByUserNameAndEmail(String userName, String email);

    User getByEmail(String email);

    int updateUser(User user, UserUpdatePutRequest userUpdatePutRequest);

    void updatePassword(User user, String newPassword);

    void updateDiaryFont(User user, int diaryFont);

    void deleteUser(User user);

    Map<String, List<String>> searchMusicGenre(User user);

    List<String> searchGiftCategory(User user);

    void changeMusicGenre(User user, UserChangeMusicPutRequest userChangeMusicPutRequest);

    void changeGiftCategory(User user, UserChangeGiftPutRequest userChangeGiftPutRequest);

}
