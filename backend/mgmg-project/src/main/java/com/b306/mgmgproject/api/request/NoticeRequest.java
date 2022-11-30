package com.b306.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel("NoticeRequest")
public class NoticeRequest {

    @NotEmpty
    @ApiModelProperty(name = "공지사항 제목", example = "공지사항 제목")
    String noticeTitle;

    @NotEmpty
    @ApiModelProperty(name = "공지사항 내용", example = "공지사항 내용")
    String noticeContent;

    @NotNull
    @ApiModelProperty(name = "상단고정여부", example = "false")
    boolean fixedFlag;

}
