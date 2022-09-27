package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SpeeachToTextResponse")
public class SpeeachToTextResponse extends BaseResponseBody {
    @ApiModelProperty(name = "공지사항 번호")
    String text;

    public static SpeeachToTextResponse of(String text, Integer statusCode, String message) {
        SpeeachToTextResponse res = new SpeeachToTextResponse();
        res.setText(text);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
