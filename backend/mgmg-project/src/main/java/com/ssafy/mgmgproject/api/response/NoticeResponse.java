package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Notice;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("NoticeResponse")
public class NoticeResponse extends BaseResponseBody {

    @ApiModelProperty(name = "공지사항 번호")
    long noticeNo;

    @ApiModelProperty(name = "공지사항 제목")
    String noticeTitle;

    @ApiModelProperty(name = "공지사항 내용")
    String noticeContent;

    @ApiModelProperty(name = "공지사항 작성날짜")
    String noticeDate;

    @ApiModelProperty(name = "상단 고정 여부")
    boolean fixedFlag;

    public static NoticeResponse of(Notice notice, Integer statusCode, String message) {
        NoticeResponse res = new NoticeResponse();
        res.setNoticeNo(notice.getNoticeNo());
        res.setNoticeTitle(notice.getNoticeTitle());
        res.setNoticeContent(notice.getNoticeContent());
        res.setNoticeDate(notice.getNoticeDate());
        res.setFixedFlag(notice.isFixedFlag());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
