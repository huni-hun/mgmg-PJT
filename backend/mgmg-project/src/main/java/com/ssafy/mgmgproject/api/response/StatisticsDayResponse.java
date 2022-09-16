package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.api.dto.StatisticsDto;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("StatisticsDayResponse")
public class StatisticsDayResponse extends BaseResponseBody{

    @ApiModelProperty(name = "선택한 요일")
    String day;

    @ApiModelProperty(name = "가장 많이 느낀 감정")
    StatisticsDto statistics;

    public static StatisticsDayResponse of(String day, StatisticsDto statistics, Integer statusCode, String message) {
        StatisticsDayResponse res = new StatisticsDayResponse();
        res.setDay(day);
        res.setStatistics(statistics);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
