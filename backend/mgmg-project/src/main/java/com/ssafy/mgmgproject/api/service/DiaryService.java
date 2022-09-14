package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.db.entity.Diary;

import java.util.List;

public interface DiaryService {

    Diary writeDiary(String userId, DiaryRequest diaryRequest);

    List<DiaryListMapping> getDiaryMonthList(Long userNo, String date);

    Diary getByDiaryNo(Long diaryNo);

}
