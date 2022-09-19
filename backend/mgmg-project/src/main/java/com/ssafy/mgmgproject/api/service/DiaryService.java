package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.request.DiaryUpdateRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.InterestGift;
import com.ssafy.mgmgproject.db.entity.InterestMusic;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface DiaryService {

    Diary writeDiary(String userId, MultipartFile multipartFile, DiaryRequest diaryRequest);

    Diary updateDiary(Long diaryNo, MultipartFile multipartFile, DiaryUpdateRequest diaryUpdateRequest);

    List<DiaryListMapping> getDiaryMonthList(Long userNo, String date) throws ParseException;

    Diary getByDiaryNo(Long diaryNo);

    int deleteDiary(Long diaryNo);

    InterestMusic addInterestMusic(String userId, Long musicNo);

    InterestGift addInterestGift(String userId, Long giftNo);

    int openGift(Long diaryNo);

    int uploadImg(Diary diary, MultipartFile multipartFile) throws IOException;
}
