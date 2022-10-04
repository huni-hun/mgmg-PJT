package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("InterestGiftDeleteRequest")
public class InterestGiftDeleteRequest {

    @NotNull
    @ApiModelProperty(name = "선물 번호", example = "3")
    Long giftNo;

}
