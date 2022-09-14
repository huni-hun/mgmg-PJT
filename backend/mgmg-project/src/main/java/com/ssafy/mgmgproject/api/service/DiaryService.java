package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.db.entity.Diary;

public interface DiaryService {

    Diary writeDiary(String userId, DiaryRequest diaryRequest);
}
