package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.AchievedBadge;
import com.ssafy.mgmgproject.db.entity.Badge;
import com.ssafy.mgmgproject.db.entity.Notification;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.AchievedBadgeRepository;
import com.ssafy.mgmgproject.db.repository.BadgeRepository;
import com.ssafy.mgmgproject.db.repository.DiaryRepository;
import com.ssafy.mgmgproject.db.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BadgeServiceImpl implements BadgeService{

    @Autowired
    BadgeRepository badgeRepository;
    @Autowired
    AchievedBadgeRepository achievedBadgeRepository;
    @Autowired
    DiaryRepository diaryRepository;
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public List<Map<String,Object>> selectBadgeList(User user) {
        List<AchievedBadge> achievedBadges = achievedBadgeRepository.findByUser(user);
        List<Badge> badges = badgeRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        int achievedBadgesIndex = 0;
        Badge badge = achievedBadgesIndex<achievedBadges.size()? achievedBadges.get(achievedBadgesIndex++).getBadge() : null;

        for(int index=0; index<badges.size(); index++){
            Map<String,Object> map = new HashMap<>();
            map.put("badgeNo",badges.get(index).getBadgeNo());
            map.put("badgeName",badges.get(index).getBadgeName());
            if(badge == badges.get(index)){
                map.put("achievedBadgeFlag",true);
                badge = achievedBadgesIndex<achievedBadges.size()? achievedBadges.get(achievedBadgesIndex++).getBadge() : null;
            }else{
                map.put("achievedBadgeFlag",false);
            }
            result.add(map);
        }
        return result;
    }

    @Override
    public List<Map<String,Object>> selectAchievedBadgeList(User user) {
        List<AchievedBadge> achievedBadges = achievedBadgeRepository.findByUser(user);
        List<Badge> badges = badgeRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();

        int achievedBadgesIndex = 0;
        Badge badge = achievedBadgesIndex<achievedBadges.size()? achievedBadges.get(achievedBadgesIndex++).getBadge() : null;

        for(int index=0; index<badges.size(); index++){
            if(badge == badges.get(index)){
                Map<String,Object> map = new HashMap<>();
                map.put("badgeNo",badges.get(index).getBadgeNo());
                map.put("badgeName",badges.get(index).getBadgeName());
                map.put("achievedBadgeFlag",true);
                result.add(map);
                if(achievedBadgesIndex<achievedBadges.size()){
                    badge = achievedBadges.get(achievedBadgesIndex++).getBadge();
                }else{
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public AchievedBadge getByUserAndBadgeNo(User user, long badgeNo) {
        Badge badge = badgeRepository.findById(badgeNo).orElse(null);
        if(badge==null){
            return null;
        }
        return achievedBadgeRepository.findByUserAndBadge(user,badge).orElse(null);
    }

    @Override
    public Badge getByBadgeNo(long badgeNo) {
        return badgeRepository.findById(badgeNo).orElse(null);
    }

    @Override
    public void checkToGetAccumulationBadge(User user) {
        long total = diaryRepository.countByUser_UserNo(user.getUserNo());
        Badge badge;

        if(total==1){
            badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining("첫","일기").orElse(null);
        }else{
            badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining(total+"","누적").orElse(null);
        }

        if(badge!=null){
            AchievedBadge achievedBadge = achievedBadgeRepository.findByUserAndBadge(user,badge).orElse(null);
            if(achievedBadge==null){
                achievedBadge = AchievedBadge.builder()
                        .user(user)
                        .badge(badge)
                        .build();
                Notification notification = Notification.builder()
                        .notificationContent(badge.getBadgeName())
                        .build();
                achievedBadgeRepository.save(achievedBadge);
                notificationRepository.save(notification);
            }
        }
    }

    @Override
    public void checkToGetEmotionBadge(User user, String emotion) {
        long total = diaryRepository.countByUser_UserNoAndEmotion(user.getUserNo(),emotion);
        Badge badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining(emotion,total+"").orElse(null);
        
        if(badge!=null){
            AchievedBadge achievedBadge = achievedBadgeRepository.findByUserAndBadge(user,badge).orElse(null);
            if(achievedBadge==null){
                achievedBadge = AchievedBadge.builder()
                        .user(user)
                        .badge(badge)
                        .build();
                Notification notification = Notification.builder()
                        .notificationContent(badge.getBadgeName())
                        .build();
                achievedBadgeRepository.save(achievedBadge);
                notificationRepository.save(notification);
            }
        }
    }
}
