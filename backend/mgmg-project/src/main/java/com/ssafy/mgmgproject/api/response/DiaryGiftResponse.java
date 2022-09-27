package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.Gift;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("DiaryGiftResponse")
public class DiaryGiftResponse extends BaseResponseBody {

    @ApiModelProperty(name = "일기 번호")
    Long diaryNo;

    @ApiModelProperty(name = "선물 오픈 여부")
    boolean openGift;

    @ApiModelProperty(name = "추천선물")
    Gift gift;


    public static DiaryGiftResponse of(Diary diary, Integer statusCode, String message) {
        DiaryGiftResponse res = new DiaryGiftResponse();
        res.setDiaryNo(diary.getDiaryNo());
        res.setOpenGift(diary.isOpenGift());
        res.setGift(diary.getGift());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
