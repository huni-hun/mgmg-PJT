package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.db.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {
    Emotion findByEmotionName(String emotionName);
}
