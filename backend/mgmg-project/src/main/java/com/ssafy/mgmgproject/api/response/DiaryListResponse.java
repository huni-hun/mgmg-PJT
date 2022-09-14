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
import java.util.List;

@Getter
@Setter
@ApiModel("DiaryResponse")
public class DiaryListResponse extends BaseResponseBody {

    @ApiModelProperty(name = "공지사항 목록")
    List<DiaryListMapping> diaries;

    public static DiaryListResponse of(List<DiaryListMapping> diaries, Integer statusCode, String message) {
        DiaryListResponse res = new DiaryListResponse();
        res.setDiaries(diaries);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
