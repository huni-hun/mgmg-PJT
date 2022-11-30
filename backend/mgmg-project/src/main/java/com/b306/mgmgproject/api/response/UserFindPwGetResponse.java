package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindPwGetResponse")
public class UserFindPwGetResponse extends BaseResponseBody {
    
    @ApiModelProperty(name = "User PW")
    String userPw;

    public static UserFindPwGetResponse of(Integer statusCode, String message, String userPw) {
        UserFindPwGetResponse res = new UserFindPwGetResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserPw(userPw);
        return res;
    }
}
