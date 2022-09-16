package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.Gift;
import com.ssafy.mgmgproject.db.entity.Music;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("DiaryResponse")
public class DiaryResponse extends BaseResponseBody {

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
    int diaryThema;

    @ApiModelProperty(name = "감정")
    String emotion;

    @ApiModelProperty(name = "추천노래")
    Music music;

    @ApiModelProperty(name = "추천선물")
    Gift gift;

    @ApiModelProperty(name = "선물오픈여부")
    Boolean openGift;

    public static DiaryResponse of(Diary diary, Integer statusCode, String message) {
        DiaryResponse res = new DiaryResponse();
        res.setDiaryContent(diary.getDiaryContent());
//        res.setDiaryDate(diary.getDiaryDate());
        res.setDay(diary.getDay());
        res.setWriteDate(diary.getWriteDate());
        res.setWeather(diary.getWeather());
        res.setDiaryImg(diary.getDiaryImg());
        res.setEmotion(diary.getEmotion());
        res.setMusic(diary.getMusic());
        res.setGift(diary.getGift());
        res.setOpenGift(diary.isOpenGift());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
