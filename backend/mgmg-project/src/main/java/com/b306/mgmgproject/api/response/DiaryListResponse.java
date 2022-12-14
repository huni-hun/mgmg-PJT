package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("DiaryListResponse")
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
