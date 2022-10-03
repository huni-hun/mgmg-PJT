package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.dto.StatisticsEmotionDto;
import com.ssafy.mgmgproject.db.repository.DiaryRepository;
import com.ssafy.mgmgproject.db.repository.DiaryRepositorySupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    DiaryRepositorySupport diaryRepositorySupport;

    @Autowired
    DiaryRepository diaryRepository;

    @Override
    public List<StatisticsEmotionDto> selectStatisticsPercentList(long userNo, String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);
        long total = diaryRepository.countByUser_UserNoAndDiaryDateBetween(userNo, startDate, endDate);
        if (total == 0) {
            return null;
        }
        List<StatisticsEmotionDto> statisticsDtos = diaryRepositorySupport.findByUser_UserNoAndDiaryDateBetweenGroupByEmotionName(userNo, startDate, endDate);
        for (int index = 0; index < statisticsDtos.size(); index++) {
            StatisticsEmotionDto statisticsDto = statisticsDtos.get(index);
            statisticsDto.setPercent((int) (((double) statisticsDto.getPercent() / (double) total) * 100));
            statisticsDtos.set(index, statisticsDto);
        }
        return statisticsDtos;
    }

    @Override
    public Map<String, List<Integer>> selectStatisticsDayList(long userNo) {
        String[] dayList = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
        String[] emotionList = {"공포", "기대", "기쁨", "사랑", "슬픔", "짜증", "창피", "평온", "피곤", "화"};
        Map<String, List<Integer>> emotionMap = new HashMap<>();
        for (String emotion : emotionList) {
            emotionMap.put(emotion, new ArrayList<Integer>());
        }
        for (String day : dayList) {
            long total = diaryRepository.countByUser_UserNoAndDay(userNo, day);
            if (total == 0) {
                for (String emotion : emotionList) {
                    List<Integer> tmp = emotionMap.get(emotion);
                    tmp.add(0);
                }
            } else {
                List<StatisticsEmotionDto> statisticsDtos = diaryRepositorySupport.findAllByUser_UserNoAndDayGroupByEmotionName(userNo, day);
                for (int index = 0; index < statisticsDtos.size(); index++) {
                    StatisticsEmotionDto statisticsDto = statisticsDtos.get(index);
                    statisticsDto.setPercent((int) (((double) statisticsDto.getPercent() / (double) total) * 100));
                    statisticsDtos.set(index, statisticsDto);
                }
                int idx = 0;
                for (String emotion : emotionList) {
                    List<Integer> tmp = emotionMap.get(emotion);
                    if (idx < statisticsDtos.size() && statisticsDtos.get(idx).getEmotion().equals(emotion)) {
                        tmp.add((int) statisticsDtos.get(idx++).getPercent());
                    } else {
                        tmp.add(0);
                    }
                }
            }
        }
        return emotionMap;
    }

    @Override
    public StatisticsEmotionDto selectStatisticsDay(long userNo, String day) {
        return diaryRepositorySupport.findByUser_UserNoAndDayGroupByEmotionName(userNo, day);
    }
}
