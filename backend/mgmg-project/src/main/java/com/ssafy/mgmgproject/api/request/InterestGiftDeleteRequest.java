package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("InterestGiftDeleteRequest")
public class InterestGiftDeleteRequest {
    @ApiModelProperty(name = "선물 번호", example = "3")
    long giftNo;
}
