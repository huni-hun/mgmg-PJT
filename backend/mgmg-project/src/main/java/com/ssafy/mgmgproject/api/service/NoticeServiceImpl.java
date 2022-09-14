package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.NoticeRequest;
import com.ssafy.mgmgproject.db.entity.Notice;
import com.ssafy.mgmgproject.db.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    @Transactional
    public boolean writeNotice(NoticeRequest noticeRequest) {
        Notice notice = Notice.builder()
                .noticeTitle(noticeRequest.getNoticeTitle())
                .noticeContent(noticeRequest.getNoticeContent())
                .fixedFlag(noticeRequest.isFixedFlag())
                .build();
        noticeRepository.save(notice);
        return true;
    }

}
