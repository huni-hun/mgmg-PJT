package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("UserEmailPostRequest")
public class UserEmailPostRequest {

    @ApiModelProperty(name = "이메일", example = "ssafy@ssafy")
    String email;
}
