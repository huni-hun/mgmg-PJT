package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.NoticeRequest;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface NoticeService {

    boolean writeNotice(NoticeRequest noticeRequest);
    Map<String,Object> selectNoticeList(String keyword, Pageable pageable);

}
