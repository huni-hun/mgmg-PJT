package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.AchievedBadge;
import com.ssafy.mgmgproject.db.entity.Badge;
import com.ssafy.mgmgproject.db.entity.User;
import com.ssafy.mgmgproject.db.repository.AchievedBadgeRepository;
import com.ssafy.mgmgproject.db.repository.BadgeRepository;
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


}
