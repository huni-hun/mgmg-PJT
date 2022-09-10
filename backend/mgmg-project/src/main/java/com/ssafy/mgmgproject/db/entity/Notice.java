package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "notice")
public class Notice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notice_no")
    private Long noticeNo;

    @Column(name="notice_title")
    private String noticeTitle;

    @Column(name="notice_content")
    private String noticeContent;

    @Column(name="notice_date")
    @Temporal(TemporalType.DATE)
    private String noticeDate;

    @Column(name="fixed_flag")
    private boolean fixedFlag;



}
