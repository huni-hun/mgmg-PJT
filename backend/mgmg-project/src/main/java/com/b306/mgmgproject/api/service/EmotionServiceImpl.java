package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.db.entity.Emotion;
import com.b306.mgmgproject.db.repository.EmotionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class EmotionServiceImpl implements EmotionService{

    @Autowired
    EmotionRepository emotionRepository;

    @Override
    public Emotion getByEmotionName(String emotionName){
        List<Emotion> emotions = emotionRepository.findByEmotionName(emotionName);
        Random random = new Random();
        int target = random.nextInt(emotions.size());
        return emotions.get(target);
    }
}
