package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("UserUpdatePwPutRequest")
public class UserUpdatePwPutRequest {

    @NotEmpty
    @ApiModelProperty(name = "유저 변경 Password", example = "new_password")
    String newPassword;

}
