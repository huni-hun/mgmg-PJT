package com.ssafy.mgmgproject.api.response;

public interface NoticeListMapping {
     Long getNoticeNo();
     String getNoticeTitle();
     String getNoticeDate();
     boolean isFixedFlag();
}
