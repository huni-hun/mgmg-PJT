package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("NoticeListResponse")
public class NoticeListResponse extends BaseResponseBody{

    @ApiModelProperty(name = "공지사항 목록")
    List<NoticeListMapping> page;

    @ApiModelProperty(name = "공지사항 목록 전체 페이지 수")
    int totalPage;

    @ApiModelProperty(name = "공지사항 목록 현재 페이지 번호")
    int currentPage;

    public static NoticeListResponse of(List<NoticeListMapping> page, int totalPage, int currentPage, Integer statusCode, String message) {
        NoticeListResponse res = new NoticeListResponse();
        res.setPage(page);
        res.setTotalPage(totalPage);
        res.setCurrentPage(currentPage);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
