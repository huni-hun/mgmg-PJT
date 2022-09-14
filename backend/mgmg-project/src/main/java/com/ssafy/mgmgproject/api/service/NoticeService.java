package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.NoticeRequest;
import com.ssafy.mgmgproject.db.entity.Notice;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface NoticeService {

    boolean writeNotice(NoticeRequest noticeRequest);
    Map<String,Object> selectNoticeList(String keyword, Pageable pageable);
    Notice getByNoticeNo(Long NoticeNo);
    boolean updateNotice(Long NoticeNo, NoticeRequest noticeRequest);
    boolean deleteNotice(Long NoticeNo);

}
