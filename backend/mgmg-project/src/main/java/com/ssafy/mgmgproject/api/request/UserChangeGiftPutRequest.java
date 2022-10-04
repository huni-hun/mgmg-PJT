package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@ApiModel("UserChangeGiftPutRequest")
public class UserChangeGiftPutRequest {

    @NotEmpty
    @ApiModelProperty(name = "선물 취향", example = "악세사리")
    List<String> giftTaste;

    @NotNull
    @ApiModelProperty(name = "최저가", example = "10000")
    Long lowPrice;

    @NotNull
    @ApiModelProperty(name = "최고가", example = "50000")
    Long highPrice;
}
