package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.api.dto.StatisticsDto;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Emotion;
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

    @ApiModelProperty(name = "요일 리스트")
    Map<String,List<StatisticsDto>> statistics;

    public static StatisticsDayListResponse of(Map<String,List<StatisticsDto>> statisticsDtos, Integer statusCode, String message) {
        StatisticsDayListResponse res = new StatisticsDayListResponse();
        res.setStatistics(statisticsDtos);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
