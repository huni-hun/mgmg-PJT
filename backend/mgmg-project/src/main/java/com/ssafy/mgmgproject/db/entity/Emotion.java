package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "emotion")
public class Emotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="emotion_no")
    private Long emotionNo;

    @Column(name="emotion_title", length = 3, nullable = false)
    private String emotionName;

    @Column(name="emotion_explanation", length = 100, nullable = false)
    private String emotionExplanation;

    @Column(name="explanation_person", length = 20, nullable = false)
    private String explanationPerson;
}