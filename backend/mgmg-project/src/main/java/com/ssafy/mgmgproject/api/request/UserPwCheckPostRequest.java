package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserPwCheckPostRequest")
public class UserPwCheckPostRequest {
    @ApiModelProperty(name = "유저 Password", example = "your_password")
    String password;
}
