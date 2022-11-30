package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.api.request.DiaryRequest;
import com.b306.mgmgproject.api.request.DiaryUpdateRequest;
import com.b306.mgmgproject.api.request.SearchItemRequest;
import com.b306.mgmgproject.db.entity.*;
import com.b306.mgmgproject.api.response.DiaryListMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DiaryService {

    Diary writeDiary(Long userNo, MultipartFile multipartFile, DiaryRequest diaryRequest);

    Diary updateDiary(Long userNo, Long diaryNo, MultipartFile multipartFile, DiaryUpdateRequest diaryUpdateRequest);

    List<DiaryListMapping> getDiaryMonthList(Long userNo, String date) throws ParseException;

    Diary getByDiaryNo(Long userNo, Long diaryNo);

    int deleteDiary(Long userNo, Long diaryNo);

    InterestMusic addInterestMusic(String userId, Long musicNo, Long diaryNo);

    InterestGift addInterestGift(String userId, Long giftNo, Long diaryNo);

    String getUserInfo(Date birth, String gender, List<String> giftCategories, Long lowPrice, Long highPrice);

    Gift writeRecommendGift(SearchItemRequest searchItemRequest, Long diaryNo, User user);

    void openGift(Diary diary, User user);

    int uploadImg(Diary diary, MultipartFile multipartFile) throws IOException;

    BadMusic addBadMusic(Long userNo, Long musicNo);

    int deleteBadMusic(Long userNo, Long diaryNo);

    String checkMusic(Long userNo, Long musicNo);

    String checkGift(Long userNo, Long giftNo);

}
