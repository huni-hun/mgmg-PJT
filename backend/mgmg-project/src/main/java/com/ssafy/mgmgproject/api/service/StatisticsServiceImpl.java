package com.ssafy.mgmgproject.api.service;


import com.ssafy.mgmgproject.api.dto.StatisticsDto;
import com.ssafy.mgmgproject.db.repository.DiaryRepository;
import com.ssafy.mgmgproject.db.repository.DiaryRepositorySurport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService{

    @Autowired
    DiaryRepositorySurport diaryRepositorySurport;

    @Autowired
    DiaryRepository diaryRepository;

    @Override
    public List<StatisticsDto> selectStatisticsPercentList(long userNo, String startDateStr, String endDateStr) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = formatter.parse(startDateStr);
        Date endDate = formatter.parse(endDateStr);

        long total = diaryRepository.countByUser_UserNoAndDiaryDateBetween(userNo,startDate,endDate);
        if(total==0){
            return null;
        }

        List<StatisticsDto> statisticsDtos = diaryRepositorySurport.findByUser_UserNoAndDiaryDateBetweenGroupByEmotionName(userNo,startDate,endDate);
        for(int index=0; index<statisticsDtos.size(); index++){
            StatisticsDto statisticsDto = statisticsDtos.get(index);
            statisticsDto.setPercent((int)(((double)statisticsDto.getPercent()/(double)total)*100));
            statisticsDtos.set(index,statisticsDto);
        }
        return statisticsDtos;
    }

}
