package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ApiModel("UserUpdatePutRequest")
public class UserUpdatePutRequest {

    @NotEmpty
    @ApiModelProperty(name = "회원 이메일", example = "ssafy@ssafy.com")
    String email;

    @NotNull
    @ApiModelProperty(name = "회원 출생일", example = "2022-09-14")
    Date birth;

    @NotEmpty
    @ApiModelProperty(name = "회원 이름", example = "김싸피")
    String name;

    @NotEmpty
    @ApiModelProperty(name = "회원 성별", example = "남자")
    String gender;

}
