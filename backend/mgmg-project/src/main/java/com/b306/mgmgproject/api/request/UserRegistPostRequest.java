package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("UserRegistPostRequest")
public class UserRegistPostRequest {

    @NotEmpty
    @ApiModelProperty(name = "회원 이메일", example = "mgmg@mgmg.com")
    String email;

    @NotEmpty
    @ApiModelProperty(name = "회원 비밀번호", example = "your password")
    String password;

    @NotEmpty
    @ApiModelProperty(name = "회원 아이디", example = "your id")
    String userId;

    @NotNull
    @ApiModelProperty(name = "회원 출생일", example = "2022-09-14")
    Date birth;

    @NotEmpty
    @ApiModelProperty(name = "회원 이름", example = "김싸피")
    String name;

    @NotEmpty
    @ApiModelProperty(name = "회원 성별", example = "남자")
    String gender;

    @NotEmpty
    Map<String, List<String>> musicTaste;

    @NotEmpty
    List<String> giftTaste;

    @NotNull
    @ApiModelProperty(name = "최저가", example = "10000")
    Long lowPrice;

    @NotNull
    @ApiModelProperty(name = "최고가", example = "50000")
    Long highPrice;
}
