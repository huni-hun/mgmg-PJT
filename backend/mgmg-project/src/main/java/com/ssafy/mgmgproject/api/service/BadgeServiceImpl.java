package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.db.entity.*;
import com.ssafy.mgmgproject.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class BadgeServiceImpl implements BadgeService{

    @Autowired
    UserRepository userRepository;
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
    @Transactional
    public void checkToGetBadge(String userId, Diary diary) throws ParseException {
        User user = userRepository.findByUserId(userId).orElse(null);
        checkToGetAccumulationBadge(user);
        checkToGetContinuousBadge(user, diary.getDiaryDate());
        checkToGetEmotionBadge(user, diary.getEmotion());
    }

    @Override
    @Transactional
    public void checkToGetAccumulationBadge(User user) {
        long total = diaryRepository.countByUser_UserNo(user.getUserNo());
        Badge badge;

        if(total==1){
            badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining("첫","일기").orElse(null);
        }else{
            badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining(total+"","누적").orElse(null);
        }
        if(badge!=null){
            achieveBadge(user, badge);
        }
    }

    @Override
    @Transactional
    public void checkToGetContinuousBadge(User user, Date date) throws ParseException {
        Diary diary = diaryRepository.findByUser_UserNoAndDiaryDate(user.getUserNo(), date).orElse(null);

        SimpleDateFormat diaryDateTransFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat writeDateTransFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date diaryDate = diary.getDiaryDate();
        String writeDate = diary.getWriteDate();
        String diaryDateStr = writeDateTransFormat.format(diaryDate);

        if(diaryDateStr.equals(writeDate.split(" ")[0])){

            Calendar cal = new GregorianCalendar(Locale.KOREA);
            cal.setTime(diaryDate);
            cal.add(Calendar.DATE, -1);

            String yDateStr = diaryDateTransFormat.format(cal.getTime());
            Date yDate = diaryDateTransFormat.parse(yDateStr);
            Diary yDiary = diaryRepository.findByUser_UserNoAndDiaryDate(user.getUserNo(), yDate).orElse(null);

            if(yDiary!=null) {

                Date yDiaryDate = yDiary.getDiaryDate();
                String yWriteDate = yDiary.getWriteDate();
                String yDiaryDateStr = writeDateTransFormat.format(yDiaryDate);

                if(yDiaryDateStr.equals(yWriteDate.split(" ")[0])){
                    int diaryContinue = user.getDiaryContinue()+1;
                    user.updateDiaryContinue(diaryContinue);
                    Badge badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining(diaryContinue+"","연속").orElse(null);
                    if(badge!=null){
                        achieveBadge(user, badge);
                    }
                    return;
                }
            }
            user.updateDiaryContinue(1);
        }
    }

    @Override
    @Transactional
    public void checkToGetEmotionBadge(User user, String emotion) {
        long total = diaryRepository.countByUser_UserNoAndEmotion(user.getUserNo(),emotion);
        Badge badge = badgeRepository.findByBadgeConditionStartingWithAndBadgeConditionContaining(emotion,total+"회").orElse(null);
        if(badge!=null){
            achieveBadge(user, badge);
        }
    }

    @Override
    @Transactional
    public void achieveBadge(User user, Badge badge) {
            AchievedBadge achievedBadge = achievedBadgeRepository.findByUserAndBadge(user,badge).orElse(null);
            if(achievedBadge==null){
                achievedBadge = AchievedBadge.builder()
                        .user(user)
                        .badge(badge)
                        .build();
                Notification notification = Notification.builder()
                        .user(user)
                        .notificationContent(badge.getBadgeName())
                        .build();
                achievedBadgeRepository.save(achievedBadge);
                notificationRepository.save(notification);
            }
        }
}
