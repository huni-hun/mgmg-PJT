package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("UserUpdateFontPutRequest")
public class UserUpdateFontPutRequest {

    @NotEmpty
    @ApiModelProperty(name = "폰트값", example = "0 / 1 / 2 ...")
    int diaryFont;

}
