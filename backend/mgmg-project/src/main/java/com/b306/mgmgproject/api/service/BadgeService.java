package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.db.entity.AchievedBadge;
import com.b306.mgmgproject.db.entity.Badge;
import com.b306.mgmgproject.db.entity.Diary;
import com.b306.mgmgproject.db.entity.User;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface BadgeService {

    List<Map<String, Object>> selectBadgeList(User user);

    List<Map<String, Object>> selectAchievedBadgeList(User user);

    AchievedBadge getByUserAndBadgeNo(User user, long badgeNo);

    Badge getByBadgeNo(long badgeNo);

    void checkToGetBadge(String userId, Diary diary) throws ParseException;

    void checkToGetAccumulationBadge(User user);

    void checkToGetContinuousBadge(User user, Date date) throws ParseException;

    void checkToGetEmotionBadge(User user, String emotion);

    void achieveBadge(User user, Badge badge);

}
