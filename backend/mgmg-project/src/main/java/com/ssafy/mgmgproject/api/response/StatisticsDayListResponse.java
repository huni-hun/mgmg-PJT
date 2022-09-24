package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.api.dto.StatisticsEmotionDto;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("StatisticsDayListResponse")
public class StatisticsDayListResponse extends BaseResponseBody{

    @ApiModelProperty(name = "감정별 요일통계 리스트")
    Map<String,List<Integer>> statistics;

    public static StatisticsDayListResponse of(Map<String,List<Integer>> statisticsDtos, Integer statusCode, String message) {
        StatisticsDayListResponse res = new StatisticsDayListResponse();
        res.setStatistics(statisticsDtos);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
