package com.ssafy.mgmgproject.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoticeRequest {
    String noticeTitle;
    String noticeContent;
    boolean fixedFlag;
}
