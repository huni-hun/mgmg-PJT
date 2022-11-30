package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserFindPwResponse")
public class UserFindPwResponse extends BaseResponseBody {
    
    @ApiModelProperty(name = "User PW")
    String userPw;

    public static UserFindPwResponse of(Integer statusCode, String message, String userPw) {
        UserFindPwResponse res = new UserFindPwResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setUserPw(userPw);
        return res;
    }
}
