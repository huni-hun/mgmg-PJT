package com.b306.mgmgproject.db.repository;

import com.b306.mgmgproject.db.entity.Notice;
import com.b306.mgmgproject.api.response.NoticeListMapping;
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
