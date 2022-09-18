package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.request.DiaryUpdateRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.*;
import com.ssafy.mgmgproject.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

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

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        DayOfWeek dayOfWeek = LocalDate.parse(formatter.format(diaryRequest.getDiaryDate())).getDayOfWeek();

        Diary diary = Diary.builder()
                .user(user)
                .diaryContent(diaryRequest.getDiaryContent())
                .diaryDate(diaryRequest.getDiaryDate())
                .day(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US))
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
    @Transactional
    public Diary updateDiary(Long diaryNo, DiaryUpdateRequest diaryUpdateRequest){
        Diary diary = diaryRepository.findByDiaryNo(diaryNo).orElse(null);
        Music music = musicRepository.findByMusicNo(diaryUpdateRequest.getMusicNo()).orElse(null);
        Gift gift = giftRepository.findByGiftNo(diaryUpdateRequest.getGiftNo()).orElse(null);
        if(diary.getGift() != gift) diary.closeGift();
        if(diary != null){
            diary.updateDiary(
                    diaryUpdateRequest.getDiaryContent(),
                    diaryUpdateRequest.getWeather(),
                    diaryUpdateRequest.getDiaryImg(),
                    diaryUpdateRequest.getDiaryThema(),
                    diaryUpdateRequest.getEmotion(),
                    music,
                    gift
            );
        }
        diaryRepository.save(diary);
        return diary;
    }

    @Override
    public List<DiaryListMapping> getDiaryMonthList(Long userNo, String date) throws ParseException {
        Calendar cal = Calendar.getInstance();
        String year = date.split("-")[0];
        String month = date.split("-")[1];
        cal.set(Integer.parseInt(year),Integer.parseInt(month)-1,1);

        String startDateStr = year+"-"+month+"-01";
        String endDateStr = year+"-"+month+"-"+Integer.toString(cal.getActualMaximum(Calendar.DAY_OF_MONTH));

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);

        List<DiaryListMapping> diaries = diaryRepository.findByUser_UserNoAndDiaryDateBetween(userNo, startDate, endDate);
        if(diaries != null) return diaries;
        else return null;
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

    @Override
    @Transactional
    public int openGift(Long diaryNo){
        Diary diary = diaryRepository.findByDiaryNo(diaryNo).orElse(null);
        if(diary == null) return 0;
        else {
            diary.openGift();
            diaryRepository.save(diary);
            return 1;
        }
    }

}