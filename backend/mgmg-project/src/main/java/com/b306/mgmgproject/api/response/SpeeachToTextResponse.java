package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("SpeeachToTextResponse")
public class SpeeachToTextResponse extends BaseResponseBody {

    @ApiModelProperty(name = "변환된 텍스트")
    String text;

    public static SpeeachToTextResponse of(String text, Integer statusCode, String message) {
        SpeeachToTextResponse res = new SpeeachToTextResponse();
        res.setText(text);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
