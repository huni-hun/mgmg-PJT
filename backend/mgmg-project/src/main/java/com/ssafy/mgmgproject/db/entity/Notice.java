package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    @Column(name="notice_no")
    private Long noticeNo;

    @Column(name="notice_title", length = 50, nullable = false)
    private String noticeTitle;

    @Column(name="notice_content", length = 500, nullable = false)
    private String noticeContent;

    @Column(name="notice_date")
    @CreatedDate
    private LocalDateTime noticeDate;

    @Column(name="fixed_flag")
    private boolean fixedFlag;

}