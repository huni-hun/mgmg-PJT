package com.ssafy.mgmgproject.api.service;

import com.ssafy.mgmgproject.api.request.NoticeRequest;
import com.ssafy.mgmgproject.api.response.NoticeListMapping;
import com.ssafy.mgmgproject.db.entity.Notice;
import com.ssafy.mgmgproject.db.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class NoticeServiceImpl implements NoticeService{

    @Autowired
    NoticeRepository noticeRepository;

    @Override
    @Transactional
    public Notice writeNotice(NoticeRequest noticeRequest) {
        Notice notice = Notice.builder()
                .noticeTitle(noticeRequest.getNoticeTitle())
                .noticeContent(noticeRequest.getNoticeContent())
                .fixedFlag(noticeRequest.isFixedFlag())
                .build();
        noticeRepository.save(notice);
        return notice;
    }

    @Override
    public Map<String,Object> selectNoticeList(String keyword, Pageable pageable) {
        if(keyword==null){
            keyword="";
        }
        List<NoticeListMapping> page = noticeRepository.findByFixedFlagTrueOrderByNoticeNoDesc();

        int rest = pageable.getPageSize() - page.size();
        int totalPage = noticeRepository.countByNoticeTitleContainingAndFixedFlagFalseOrNoticeContentContainingAndFixedFlagFalse(keyword,keyword);

        if(0<totalPage%rest) totalPage=(totalPage/rest)+1;
        else totalPage=totalPage/rest;

        if(pageable.getPageNumber()<0 || totalPage<pageable.getPageNumber()+1){
            return null;
        }

        Pageable paging = PageRequest.of(pageable.getPageNumber(),rest, Sort.Direction.DESC,"noticeDate");
        List<NoticeListMapping> notices = noticeRepository.findByNoticeTitleContainingAndFixedFlagFalseOrNoticeContentContainingAndFixedFlagFalseOrderByNoticeNoDesc(keyword,keyword,paging);
        page.addAll(notices);

        Map<String,Object> result = new HashMap<>();
        result.put("page",page);
        result.put("totalPage",totalPage);
        result.put("currentPage",pageable.getPageNumber()+1);
        return result;
    }

    @Override
    public Notice getByNoticeNo(Long NoticeNo) {
        return noticeRepository.findById(NoticeNo).orElse(null);
    }

    @Override
    @Transactional
    public Notice updateNotice(Long NoticeNo, NoticeRequest noticeRequest) {
        Notice notice = getByNoticeNo(NoticeNo);
        if(notice==null){
            return null;
        }
        notice.updateNotice(noticeRequest.getNoticeTitle(), noticeRequest.getNoticeContent(), noticeRequest.isFixedFlag());
        noticeRepository.save(notice);
        return notice;
    }

    @Override
    @Transactional
    public boolean deleteNotice(Long NoticeNo) {
        Optional<Notice> result = noticeRepository.findById(NoticeNo);
        if(!result.isPresent()){
            return  false;
        }
        result.ifPresent(notice -> {
            noticeRepository.delete(notice);
        });
        return true;
    }

}
