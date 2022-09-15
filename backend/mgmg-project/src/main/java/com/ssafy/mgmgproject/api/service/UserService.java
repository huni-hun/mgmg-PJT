package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.UserChangeGiftPutRequest;
import com.ssafy.mgmgproject.api.request.UserChangeMusicPutRequest;
import com.ssafy.mgmgproject.api.request.UserRegistPostRequest;
import com.ssafy.mgmgproject.api.request.UserUpdatePutRequest;
import com.ssafy.mgmgproject.db.entity.GiftCategory;
import com.ssafy.mgmgproject.db.entity.MusicGenre;
import com.ssafy.mgmgproject.db.entity.User;

import java.util.List;

public interface UserService {
    User getByUserId(String userId);

    void createUser(UserRegistPostRequest userRegistInfo);

    User getByUserIdAndEmail(String userId, String email);

    User getByUserNameAndEmail(String userName, String email);

    User getByEmail(String email);

    int updateUser(User user, UserUpdatePutRequest userUpdatePutRequest);

    void updatePassword(User user, String newPassword);

    void updateDiaryFont(User user, int diaryFont);

    void deleteUser(String userId);

    List<MusicGenre> searchMusicGenre(User user);

    List<GiftCategory> searchGiftCategory(User user);

    void changeMusicGenre(User user, UserChangeMusicPutRequest userChangeMusicPutRequest);

    void changeGiftCategory(User user, UserChangeGiftPutRequest userChangeGiftPutRequest);
}
