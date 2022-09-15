package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.UserRegistPostRequest;
import com.ssafy.mgmgproject.api.request.UserUpdatePutRequest;
import com.ssafy.mgmgproject.db.entity.GiftCategory;
import com.ssafy.mgmgproject.db.entity.MusicGenre;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.GiftCategoryRepository;
import com.ssafy.mgmgproject.db.repository.MusicGenreRepository;
import com.ssafy.mgmgproject.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MusicGenreRepository musicGenreRepository;

    @Autowired
    GiftCategoryRepository giftCategoryRepository;

    @Override
    public User getByUserId(String userId) {
        return userRepository.findByUserId(userId).orElse(null);
    }

    @Override
    public void createUser(UserRegistPostRequest userRegistInfo) {
        User user = User.builder()
                .userId(userRegistInfo.getUserId())
                .password(passwordEncoder.encode(userRegistInfo.getPassword()))
                .email(userRegistInfo.getEmail())
                .birth(userRegistInfo.getBirth())
                .userName(userRegistInfo.getName())
                .gender(userRegistInfo.getGender())
                .build();
        userRepository.save(user);

        for (String taste : userRegistInfo.getMusicTaste()) {
            MusicGenre musicTaste = MusicGenre.builder()
                            .user(user)
                            .musicGenreName(taste)
                            .build();
            musicGenreRepository.save(musicTaste);
        }

        for (String taste : userRegistInfo.getGiftTaste()) {
            GiftCategory giftCategory = GiftCategory.builder()
                            .user(user)
                            .giftCategoryName(taste)
                            .build();
            giftCategoryRepository.save(giftCategory);
        }
    }

    @Override
    public User getByUserIdAndEmail(String userId, String email) {
        return userRepository.findByUserIdAndEmail(userId, email).orElse(null);
    }

    @Override
    public User getByUserNameAndEmail(String userName, String email) {
        return userRepository.findByUserNameAndEmail(userName, email).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    @Transactional
    public int updateUser(User user, UserUpdatePutRequest userUpdatePutRequest) {
        String email = userUpdatePutRequest.getEmail();
        Date birth = userUpdatePutRequest.getBirth();
        String userName = userUpdatePutRequest.getName();
        String gender = userUpdatePutRequest.getGender();
        try {
            user.updateUser(email, birth, userName, gender);
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional
    public void updatePassword(User user, String newPassword) {
        user.updatePw(passwordEncoder.encode(newPassword));
    }

    @Override
    public void updateDiaryFont(User user, int diaryFont) {
        user.updateFont(diaryFont);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
    }

    @Override
    public List<MusicGenre> searchMusicGenre(User user) {
        return musicGenreRepository.findByUser(user);
    }

    @Override
    public List<GiftCategory> searchGiftCategory(User user) {
        return giftCategoryRepository.findByUser(user);
    }

}
