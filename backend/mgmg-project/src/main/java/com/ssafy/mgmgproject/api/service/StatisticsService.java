package com.ssafy.mgmgproject.api.service;


import com.ssafy.mgmgproject.api.dto.StatisticsDto;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

public interface StatisticsService {
    List<StatisticsDto> selectStatisticsPercentList(long userNo, String startDateStr, String endDateStr) throws ParseException;
    Map<String, List<StatisticsDto>> selectStatisticsDayList(long userNo);

}
