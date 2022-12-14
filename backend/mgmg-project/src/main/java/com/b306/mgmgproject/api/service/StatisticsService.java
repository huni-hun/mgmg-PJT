package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.api.dto.StatisticsEmotionDto;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface StatisticsService {

    List<StatisticsEmotionDto> selectStatisticsPercentList(long userNo, String startDateStr, String endDateStr) throws ParseException;

    Map<String, List<Integer>> selectStatisticsDayList(long userNo);

    StatisticsEmotionDto selectStatisticsDay(long userNo, String day);

}
