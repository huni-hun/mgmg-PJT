package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.*;
import com.ssafy.mgmgproject.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Autowired
    InterestMusicRepository interestMusicRepository;

    @Autowired
    InterestGiftRepository interestGiftRepository;

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

    @Override
    public List<DiaryListMapping> getDiaryMonthList(Long userNo, String date){
        List<DiaryListMapping> diaries = diaryRepository.findByUser_UserNoAndDiaryDateContaining(userNo, date);
        if(diaries != null) return diaries;
        return null;
    }

    @Override
    public Diary getByDiaryNo(Long diaryNo) {
        return diaryRepository.findByDiaryNo(diaryNo).orElse(null);
    }

    @Override
    @Transactional
    public int deleteDiary(Long diaryNo) {
        try {
            diaryRepository.findByDiaryNo(diaryNo).get();
        } catch (Exception e) {
            return 0;
        }
        diaryRepository.deleteByDiaryNo(diaryNo);
        return 1;
    }

    @Override
    public InterestMusic addInterestMusic(String userId, Long musicNo){
        User user = userRepository.findByUserId(userId).orElse(null);
        Music music = musicRepository.findByMusicNo(musicNo).orElse(null);
        InterestMusic interestMusic = InterestMusic.builder()
                .user(user)
                .music(music)
                .build();
        interestMusicRepository.save(interestMusic);
        return interestMusic;
    }

    @Override
    public InterestGift addInterestGift(String userId, Long giftNo){
        User user = userRepository.findByUserId(userId).orElse(null);
        Gift gift = giftRepository.findByGiftNo(giftNo).orElse(null);
        InterestGift interestGift = InterestGift.builder()
                .user(user)
                .gift(gift)
                .build();
        interestGiftRepository.save(interestGift);
        return interestGift;
    }

}