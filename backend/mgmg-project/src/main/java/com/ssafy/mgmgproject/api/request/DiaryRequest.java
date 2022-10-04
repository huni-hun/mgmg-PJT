package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Service
@ApiModel("CreateDiaryPostRequest")
public class DiaryRequest {

    @NotBlank
    @ApiModelProperty(name = "일기 내용", example = "일기 내용입니다.")
    String diaryContent;

    @NotNull
    @ApiModelProperty(name = "해당 날짜", example = "2022-09-14")
    Date diaryDate;

    @NotEmpty
    @ApiModelProperty(name = "날씨", example = "맑음")
    String weather;

    @NotEmpty
    @ApiModelProperty(name = "편지지", example = "편지지1")
    String diaryThema;

    @NotEmpty
    @ApiModelProperty(name = "감정", example = "슬픔")
    String emotion;

    @NotNull
    @ApiModelProperty(name = "노래 번호", example = "노래 번호")
    Long musicNo;

    @NotNull
    @ApiModelProperty(name = "선물 번호", example = "선물 번호")
    Long giftNo;
}
