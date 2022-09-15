package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.api.dto.StatisticsDto;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Emotion;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("NotificationListResponse")
public class StatisticsPercentListResponse extends BaseResponseBody{

    @ApiModelProperty(name = "통계 리스트")
    List<StatisticsDto> statisticsDtos;

    @ApiModelProperty(name = "가장 많이 느낀 감정")
    String emotionName;

    @ApiModelProperty(name = "가장 많이 느낀 감정 명언")
    String emotionExplanation;

    @ApiModelProperty(name = "가장 많이 느낀 감정 명언가")
    String explanationPerson;

    public static StatisticsPercentListResponse of(List<StatisticsDto> statisticsDtos, Emotion emotion, Integer statusCode, String message) {
        StatisticsPercentListResponse res = new StatisticsPercentListResponse();
        res.setStatisticsDtos(statisticsDtos);
        res.setEmotionName(emotion.getEmotionName());
        res.setEmotionExplanation(emotion.getEmotionExplanation());
        res.setExplanationPerson(emotion.getExplanationPerson());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
