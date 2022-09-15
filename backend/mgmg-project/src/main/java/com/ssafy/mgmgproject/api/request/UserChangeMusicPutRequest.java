package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ApiModel("UserChangeMusicPutRequest")
public class UserChangeMusicPutRequest {
    @ApiModelProperty(name = "음악 취향", example = "발라드")
    List<String> musicTaste;
}
