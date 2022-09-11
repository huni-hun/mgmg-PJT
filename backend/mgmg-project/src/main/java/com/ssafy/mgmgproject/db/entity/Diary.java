package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_no")
    private Long diaryNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @Column(name = "diary_content")
    private String diaryContent;

    @Column(name = "diary_date")
    private String diaryDate;

    @Column(name = "write_date")
    private String writeDate;

    @Column(name = "weather")
    private String weather;

    @Column(name = "diary_img")
    private String diaryImg;

    @Column(name = "emotion")
    private String emotion;

    @Column(name = "diary_thema")
    private int diaryThema;

    @Column(name = "recommend_music")
    private Long recommendMusic;

    @Column(name = "recommend_gift")
    private Long recommendGift;

    @Column(name = "open_gift")
    private boolean openGift;
}
