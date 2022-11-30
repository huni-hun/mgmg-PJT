package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.Diary;
import com.b306.mgmgproject.db.entity.Gift;
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

    @ApiModelProperty(name = "추천선물 관심 체크 여부")
    String checkGift;

    public static DiaryGiftResponse of(Diary diary, String checkGift, Integer statusCode, String message) {
        DiaryGiftResponse res = new DiaryGiftResponse();
        res.setDiaryNo(diary.getDiaryNo());
        res.setOpenGift(diary.isOpenGift());
        res.setGift(diary.getGift() != null ? diary.getGift() : null);
        res.setCheckGift(checkGift);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
