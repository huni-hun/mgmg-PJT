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
@Table(name = "achievement_condition")
public class AchievementCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="achievement_condition_no")
    private Long achievementConditionNo;

    @Column(name = "achievement_condition_tag", length = 2)
    private String achievementConditionTag;

    @Column(name = "achievement_condition_count")
    private int achievementConditionCount;

}
