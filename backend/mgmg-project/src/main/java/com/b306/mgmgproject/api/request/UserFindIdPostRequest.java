package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("UserFindIdPostRequest")
public class UserFindIdPostRequest {

    @NotEmpty
    @ApiModelProperty(name = "이름", example = "김싸피")
    String userName;

    @NotEmpty
    @ApiModelProperty(name = "이메일", example = "mgmg@mgmg.com")
    String email;

}
