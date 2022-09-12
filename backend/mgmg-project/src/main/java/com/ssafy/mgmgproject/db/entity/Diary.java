package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

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

    @Column(name = "diary_content", length = 500)
    private String diaryContent;

    @Column(name = "diary_date")
    @Temporal(TemporalType.DATE)
    private Date diaryDate;

    @Column(name = "write_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date writeDate;

    @Column(name = "weather", length = 5)
    private String weather;

    @Column(name = "diary_img", length = 255)
    private String diaryImg;

    @Column(name = "emotion", length = 3)
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
