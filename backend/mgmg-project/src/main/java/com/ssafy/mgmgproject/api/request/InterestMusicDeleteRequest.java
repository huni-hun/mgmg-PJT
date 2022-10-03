package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ApiModel("InterestMusicDeleteRequest")
public class InterestMusicDeleteRequest {

    @NotEmpty
    @ApiModelProperty(name = "음악 번호", example = "3")
    long musicNo;

}
