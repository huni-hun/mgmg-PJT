package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.Music;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DiaryMusicResponse")
public class DiaryMusicResponse extends BaseResponseBody {

    @ApiModelProperty(name = "일기 번호")
    Long diaryNo;

    @ApiModelProperty(name = "추천노래 관심 혹은 비추천 체크 여부")
    String checkMusic;

    @ApiModelProperty(name = "추천노래")
    Music music;

    public static DiaryMusicResponse of(Diary diary, String checkMusic, Integer statusCode, String message) {
        DiaryMusicResponse res = new DiaryMusicResponse();
        res.setDiaryNo(diary.getDiaryNo());
        res.setMusic(diary.getMusic());
        res.setCheckMusic(checkMusic);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
