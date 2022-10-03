package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindIdGetResponse")
public class UserFindIdGetResponse extends BaseResponseBody {

    @ApiModelProperty(name = "User ID")
    String userId;

    public static UserFindIdGetResponse of(Integer statusCode, String message, String userId) {
        UserFindIdGetResponse res = new UserFindIdGetResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserId(userId);
        return res;
    }
}
