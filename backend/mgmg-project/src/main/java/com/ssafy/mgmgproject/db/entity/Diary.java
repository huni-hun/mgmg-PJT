package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @Column(name = "diary_date", length = 16)
    private String diaryDate;

    @Column(name = "day", length = 3)
    private String day;

    @Column(name = "write_date", length = 16)
    private String writeDate;

    @Column(name = "weather", length = 5)
    private String weather;

    @Column(name = "diary_img", length = 255)
    private String diaryImg;

    @Column(name = "emotion", length = 3)
    private String emotion;

    @Column(name = "diary_thema")
    private int diaryThema;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_no")
    private Music music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_no")
    private Gift gift;

    @Column(name = "open_gift")
    private boolean openGift;

    @PrePersist
    public void onPrePersist(){
        this.writeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
    @PreUpdate
    public void onPreUpdate(){
        this.writeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}
