package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("InterestMusicDeleteRequest")
public class InterestMusicDeleteRequest {

    @NotNull
    @ApiModelProperty(name = "음악 번호", example = "3")
    Long musicNo;

}
