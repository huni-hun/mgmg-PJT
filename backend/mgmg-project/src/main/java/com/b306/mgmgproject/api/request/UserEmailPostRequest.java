package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("UserEmailPostRequest")
public class UserEmailPostRequest {

    @NotEmpty
    @ApiModelProperty(name = "이메일", example = "mgmg@mgmg.com")
    String email;

}
