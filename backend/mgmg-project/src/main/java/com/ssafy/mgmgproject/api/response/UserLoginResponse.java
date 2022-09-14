package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@ApiModel("UserLoginResponse")
public class UserLoginResponse extends BaseResponseBody {

    @ApiModelProperty(name = "JWT 인증 토큰", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
    String accessToken;

    @ApiModelProperty(name = "회원 이메일")
    String email;

    @ApiModelProperty(name = "회원 아이디")
    String userId;

    @ApiModelProperty(name = "회원 이름")
    String userName;

    @ApiModelProperty(name = "회원 생일")
    String birth;

    @ApiModelProperty(name = "회원 성별")
    String gender;

    @ApiModelProperty(name = "회원 설정 글꼴")
    int diaryFont;

    @ApiModelProperty(name = "관리자 여부")
    boolean admin;



    public static UserLoginResponse of(User user, Integer statusCode, String message, String accessToken) {
        UserLoginResponse res = new UserLoginResponse();
        res.setStatusCode(statusCode);
        res.setEmail(user.getEmail());
        res.setUserId(user.getUserId());
        res.setUserName(user.getUsername());
        res.setBirth(user.getBirth().toString());
//        res.getGender();
        res.setDiaryFont(user.getDiaryFont());
        res.setAdmin(user.isAdmin());
        res.setMessage(message);
        res.setAccessToken(accessToken);
        return res;
    }

}
