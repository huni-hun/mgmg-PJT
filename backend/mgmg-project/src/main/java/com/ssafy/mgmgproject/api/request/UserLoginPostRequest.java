package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("UserLoginRequest")
public class UserLoginPostRequest {

    @NotEmpty
    @ApiModelProperty(name = "회원 아이디", example = "user_id")
    String userId;

    @NotEmpty
    @ApiModelProperty(name = "회원 비밀번호", example = "user_password")
    String password;

    @NotNull
    @ApiModelProperty(name = "회원 자동로그인 여부", example = "user_auto_flag")
    boolean autoFlag;

}
