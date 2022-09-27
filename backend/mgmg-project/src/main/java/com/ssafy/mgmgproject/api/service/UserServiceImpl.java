package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.UserChangeGiftPutRequest;
import com.ssafy.mgmgproject.api.request.UserChangeMusicPutRequest;
import com.ssafy.mgmgproject.api.request.UserRegistPostRequest;
import com.ssafy.mgmgproject.api.request.UserUpdatePutRequest;
import com.ssafy.mgmgproject.db.entity.GiftCategory;
import com.ssafy.mgmgproject.db.entity.MusicGenre;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.GiftCategoryRepository;
import com.ssafy.mgmgproject.db.repository.MusicGenreRepository;
import com.ssafy.mgmgproject.db.repository.MusicGenreRepositorySupport;
import com.ssafy.mgmgproject.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    final String[] emotionList = {"공포", "기대", "기쁨", "사랑", "슬픔", "짜증", "창피", "평온", "피곤", "화"};

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MusicGenreRepository musicGenreRepository;

    @Autowired
    GiftCategoryRepository giftCategoryRepository;

    @Autowired
    MusicGenreRepositorySupport musicGenreRepositorySupport;

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
                .lowPrice(userRegistInfo.getLowPrice())
                .highPrice(userRegistInfo.getHighPrice())
                .build();
        userRepository.save(user);


        Map<String,List<String>> musicTasteMap = userRegistInfo.getMusicTaste();
        for(String emotion: emotionList){
            List<String> musicGenreList = musicTasteMap.get(emotion);
            for (String musicGenre : musicGenreList) {
                MusicGenre musicTaste = MusicGenre.builder()
                        .user(user)
                        .emotionName(emotion)
                        .musicGenreName(musicGenre)
                        .build();
                musicGenreRepository.save(musicTaste);
            }
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
    @Transactional
    public void updateDiaryFont(User user, int diaryFont) {
        user.updateFont(diaryFont);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        user.deleteUser();
    }

    @Override
    public Map<String,List<String>> searchMusicGenre(User user) {

        Map<String,List<String>> musicTaste= new HashMap<>();
        List<MusicGenre> musicGenres = musicGenreRepository.findByUser(user);

        for(String emotion: emotionList){
            musicTaste.put(emotion,new ArrayList<>());
        }

        for(MusicGenre musicGenre: musicGenres){
            musicTaste.get(musicGenre.getEmotionName()).add(musicGenre.getMusicGenreName());
        }

        return musicTaste;
    }

    @Override
    public List<String> searchGiftCategory(User user) {
        List<GiftCategory> gitCategories = giftCategoryRepository.findByUser(user);
        List<String> list = new ArrayList<>();
        for(GiftCategory giftCategory : gitCategories){
            list.add(giftCategory.getGiftCategoryName());
        }
        return list;
    }

    @Override
    @Transactional
    public void changeMusicGenre(User user, UserChangeMusicPutRequest userChangeMusicPutRequest) {
        musicGenreRepositorySupport.deleteByUser(user);

        Map<String,List<String>> musicTasteMap = userChangeMusicPutRequest.getMusicTaste();

        for(String emotion: emotionList){
            List<String> musicGenreList = musicTasteMap.get(emotion);
            for (String musicGenre : musicGenreList) {
                MusicGenre musicTaste = MusicGenre.builder()
                        .user(user)
                        .emotionName(emotion)
                        .musicGenreName(musicGenre)
                        .build();
                musicGenreRepository.save(musicTaste);
            }
        }

    }

    @Override
    @Transactional
    public void changeGiftCategory(User user, UserChangeGiftPutRequest userChangeGiftPutRequest) {
        giftCategoryRepository.deleteByUser(user);

        for (String taste :userChangeGiftPutRequest.getGiftTaste()) {
            GiftCategory giftCategory = GiftCategory.builder()
                    .user(user)
                    .giftCategoryName(taste)
                    .build();
            giftCategoryRepository.save(giftCategory);
        }

        user.updateUserPrice(userChangeGiftPutRequest.getLowPrice(), userChangeGiftPutRequest.getHighPrice());
    }

}
