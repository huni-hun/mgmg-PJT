package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.Diary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;

@Getter
@Setter
@ApiModel("DiaryResponse")
public class DiaryResponse extends BaseResponseBody {

    @ApiModelProperty(name = "일기 번호")
    Long diaryNo;

    @ApiModelProperty(name = "일기 내용")
    String diaryContent;

    @ApiModelProperty(name = "해당 날짜")
    String diaryDate;

    @ApiModelProperty(name = "요일")
    String day;

    @ApiModelProperty(name = "작성 날짜")
    String writeDate;

    @ApiModelProperty(name = "날씨")
    String weather;

    @ApiModelProperty(name = "사진")
    String diaryImg;

    @ApiModelProperty(name = "편지지")
    String diaryThema;

    @ApiModelProperty(name = "감정")
    String emotion;

    public static DiaryResponse of(Diary diary, Integer statusCode, String message) {
        DiaryResponse res = new DiaryResponse();
        res.setDiaryNo(diary.getDiaryNo());
        res.setDiaryContent(diary.getDiaryContent());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String diaryDate = dateFormat.format(diary.getDiaryDate());
        res.setDiaryDate(diaryDate);
        res.setDay(diary.getDay());
        res.setWriteDate(diary.getWriteDate());
        res.setWeather(diary.getWeather());
        res.setDiaryImg(diary.getDiaryImg());
        res.setEmotion(diary.getEmotion());
        res.setDiaryThema(diary.getDiaryThema());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
