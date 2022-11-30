package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.Emotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    List<Emotion> findByEmotionName(String emotionName);

}
