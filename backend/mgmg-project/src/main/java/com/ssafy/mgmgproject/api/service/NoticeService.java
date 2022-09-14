package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.NoticeRequest;

public interface NoticeService {

    boolean writeNotice(NoticeRequest noticeRequest);

}
