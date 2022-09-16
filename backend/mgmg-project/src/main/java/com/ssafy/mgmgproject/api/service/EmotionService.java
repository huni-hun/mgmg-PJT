package com.ssafy.mgmgproject.api.service;


import com.ssafy.mgmgproject.db.entity.Emotion;

public interface EmotionService {
    Emotion getByEmotionName(String emotionName);
}
