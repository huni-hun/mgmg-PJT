package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserUpdatePwPutRequest")
public class UserUpdatePwPutRequest {
    @ApiModelProperty(name = "유저 기존 Password", example = "your_password")
    String password;

    @ApiModelProperty(name = "유저 변경 Password", example = "new_password")
    String newPassword;
}
