package com.ssafy.mgmgproject.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Service
@ApiModel("CreateDiaryPostRequest")
public class DiaryRequest {

    @NotEmpty
    @ApiModelProperty(name = "일기 내용", example = "일기 내용입니다.")
    String diaryContent;

    @NotEmpty
    @ApiModelProperty(name = "해당 날짜", example = "2022-09-14")
    String diaryDate;

    @NotEmpty
    @ApiModelProperty(name = "요일", example = "WED")
    String day;

    @NotEmpty
    @ApiModelProperty(name = "날씨", example = "맑음")
    String weather;

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
