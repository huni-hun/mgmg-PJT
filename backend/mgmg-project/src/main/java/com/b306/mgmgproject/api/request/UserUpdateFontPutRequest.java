package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("UserUpdateFontPutRequest")
public class UserUpdateFontPutRequest {

    @NotNull
    @ApiModelProperty(name = "폰트값", example = "0 / 1 / 2 ...")
    Integer diaryFont;

}
