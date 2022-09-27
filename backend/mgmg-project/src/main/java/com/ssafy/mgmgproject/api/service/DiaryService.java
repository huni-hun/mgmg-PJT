package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.request.SearchItemRequest;
import com.ssafy.mgmgproject.api.request.DiaryUpdateRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DiaryService {

    Diary writeDiary(Long userNo, MultipartFile multipartFile, DiaryRequest diaryRequest);

    Diary updateDiary(Long diaryNo, MultipartFile multipartFile, DiaryUpdateRequest diaryUpdateRequest);

    List<DiaryListMapping> getDiaryMonthList(Long userNo, String date) throws ParseException;

    Diary getByDiaryNo(Long diaryNo);

    int deleteDiary(Long diaryNo);

    InterestMusic addInterestMusic(String userId, Long musicNo);

    InterestGift addInterestGift(String userId, Long giftNo);

    String getUserInfo(Date birth, String gender, List<String> giftCategories, Long lowPrice, Long highPrice);

    Gift writeRecommendGift(SearchItemRequest searchItemRequest, Long diaryNo, User user);

    int openGift(Long diaryNo, User user);

    int uploadImg(Diary diary, MultipartFile multipartFile) throws IOException;

    BadMusic addBadMusic(String userId, Long musicNo);

    int deleteBadMusic(String userId, Long musicNo);
}
