package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@ApiModel("UserResponse")
public class UserMyPageResponse extends BaseResponseBody {
    
    @ApiModelProperty(name = "User ID")
    String userId;

    @ApiModelProperty(name = "User Name")
    String userName;

    @ApiModelProperty(name = "User Email")
    String email;

    @ApiModelProperty(name = "User Birth")
    Date birth;

    @ApiModelProperty(name = "User Gender")
    String gender;

    public static UserMyPageResponse of(User user, Integer statusCode, String message) {
        UserMyPageResponse res = new UserMyPageResponse();
        res.setUserId(user.getUserId());
        res.setUserName(user.getUserName());
        res.setEmail(user.getEmail());
        res.setBirth(user.getBirth());
        res.setGender(user.getGender());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
