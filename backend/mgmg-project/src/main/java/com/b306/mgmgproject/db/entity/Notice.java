package com.b306.mgmgproject.db.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
@EntityListeners(AuditingEntityListener.class)
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_no")
    private Long noticeNo;

    @Column(name = "notice_title", length = 50, nullable = false)
    private String noticeTitle;

    @Column(name = "notice_content", length = 500, nullable = false)
    private String noticeContent;

    @Column(name = "notice_date", updatable = false, length = 16)
    @CreatedDate
    private String noticeDate;

    @Column(name = "fixed_flag")
    private boolean fixedFlag;

    @PrePersist
    public void onPrePersist() {
        this.noticeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public void updateNotice(String noticeTitle, String noticeContent, boolean fixedFlag) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.fixedFlag = fixedFlag;
    }
}