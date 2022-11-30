package com.b306.mgmgproject.api.service;

import com.b306.mgmgproject.api.request.NoticeRequest;
import com.b306.mgmgproject.db.entity.Notice;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface NoticeService {

    Notice writeNotice(NoticeRequest noticeRequest);

    Map<String, Object> selectNoticeList(String keyword, Pageable pageable);

    Notice getByNoticeNo(Long NoticeNo);

    Notice updateNotice(Long NoticeNo, NoticeRequest noticeRequest);

    boolean deleteNotice(Long NoticeNo);

}
