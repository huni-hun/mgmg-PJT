package com.ssafy.mgmgproject.db.repository;

import com.ssafy.mgmgproject.api.response.NoticeListMapping;
import com.ssafy.mgmgproject.db.entity.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<NoticeListMapping> findByFixedFlagTrueOrderByNoticeNoDesc();

    List<NoticeListMapping> findByNoticeTitleContainingAndFixedFlagFalseOrNoticeContentContainingAndFixedFlagFalseOrderByNoticeNoDesc(String titleKeyword, String contentKeyword, Pageable pageable);

    Integer countByNoticeTitleContainingAndFixedFlagFalseOrNoticeContentContainingAndFixedFlagFalse(String titleKeyword, String contentKeyword);

}
