package com.ssafy.mgmgproject.api.service;


import com.ssafy.mgmgproject.db.entity.Emotion;
import com.ssafy.mgmgproject.db.repository.EmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmotionServiceImpl implements EmotionService{

    @Autowired
    EmotionRepository emotionRepository;

    @Override
    public Emotion getByEmotionName(String emotionName){
        return emotionRepository.findByEmotionName(emotionName);
    }
}
