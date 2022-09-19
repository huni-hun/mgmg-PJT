package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;

@Getter
@Service
@ApiModel("UpdateDiaryPutRequest")
public class DiaryUpdateRequest {

    @NotEmpty
    @ApiModelProperty(name = "일기 내용", example = "일기 내용입니다.")
    String diaryContent;

    @NotEmpty
    @ApiModelProperty(name = "날씨", example = "맑음")
    String weather;

    @ApiModelProperty(name = "사진", example = "사진")
    String diaryImg;

    @NotEmpty
    @ApiModelProperty(name = "편지지", example = "1")
    int diaryThema;

    @NotEmpty
    @ApiModelProperty(name = "감정", example = "슬픔")
    String emotion;

    @NotEmpty
    @ApiModelProperty(name = "노래 번호", example = "노래 번호")
    Long musicNo;

    @NotEmpty
    @ApiModelProperty(name = "선물 번호", example = "선물 번호")
    Long giftNo;
}