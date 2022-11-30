package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.db.entity.Emotion;

public interface EmotionService {
    Emotion getByEmotionName(String emotionName);
}
