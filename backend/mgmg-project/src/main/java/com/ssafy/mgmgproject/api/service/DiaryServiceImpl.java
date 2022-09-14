package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.Gift;
import com.ssafy.mgmgproject.db.entity.Music;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.DiaryRepository;
import com.ssafy.mgmgproject.db.repository.GiftRepository;
import com.ssafy.mgmgproject.db.repository.MusicRepository;
import com.ssafy.mgmgproject.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MusicRepository musicRepository;

    @Autowired
    GiftRepository giftRepository;

    @Override
    public Diary writeDiary(String userId, DiaryRequest diaryRequest) {
        User user = userRepository.findByUserId(userId).orElse(null);
        Music music = musicRepository.findByMusicNo(diaryRequest.getMusicNo()).orElse(null);
        Gift gift = giftRepository.findByGiftNo(diaryRequest.getGiftNo()).orElse(null);
        Diary diary = Diary.builder()
                .user(user)
                .diaryContent(diaryRequest.getDiaryContent())
                .diaryDate(diaryRequest.getDiaryDate())
                .weather(diaryRequest.getWeather())
                .diaryThema(diaryRequest.getDiaryThema())
                .emotion(diaryRequest.getEmotion())
                .music(music)
                .gift(gift)
                .openGift(false)
                .build();
        diaryRepository.save(diary);
        return diary;
    }
}
