package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserLoginResponse")
public class UserLoginResponse extends BaseResponseBody {

    @ApiModelProperty(name = "JWT 인증 access 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;

    @ApiModelProperty(name = "JWT 인증 refresh 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String refreshToken;

    @ApiModelProperty(name = "회원 이름")
    String userName;

    @ApiModelProperty(name = "회원 설정 글꼴")
    int diaryFont;

    @ApiModelProperty(name = "관리자 여부")
    boolean admin;

    public static UserLoginResponse of(User user, Integer statusCode, String message, String accessToken, String refreshToken) {
        UserLoginResponse res = new UserLoginResponse();
        res.setStatusCode(statusCode);
        res.setUserName(user.getUserName());
        res.setDiaryFont(user.getDiaryFont());
        res.setAdmin(user.isAdmin());
        res.setMessage(message);
        res.setAccessToken(accessToken);
        res.setRefreshToken(refreshToken);
        return res;
    }

}
