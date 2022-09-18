package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.AchievedBadge;
import com.ssafy.mgmgproject.db.entity.Badge;
import com.ssafy.mgmgproject.db.entity.User;

import java.util.List;
import java.util.Map;

public interface BadgeService {

    List<Map<String,Object>> selectBadgeList(User user);
    List<Map<String,Object>> selectAchievedBadgeList(User user);
    AchievedBadge getByUserAndBadgeNo(User user, long badgeNo);
    Badge getByBadgeNo(long badgeNo);

    void checkToGetAccumulationBadge(User user);
    void checkToGetEmotionBadge(User user, String emotion);
}
