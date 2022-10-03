package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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

    @Column(name = "diary_date")
    @Temporal(TemporalType.DATE)
    private Date diaryDate;

    @Column(name = "day", length = 3)
    private String day;

    @Column(name = "write_date", length = 16)
    private String writeDate;

    @Column(name = "weather", length = 9)
    private String weather;

    @Column(name = "diary_img", length = 255)
    private String diaryImg;

    @Column(name = "emotion", length = 3)
    private String emotion;

    @Column(name = "diary_thema", length = 30)
    private String diaryThema;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "music_no")
    private Music music;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_no")
    private Gift gift;

    @Column(name = "open_gift")
    private boolean openGift;

    @PrePersist
    public void onPrePersist() {
        this.writeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    @PreUpdate
    public void onPreUpdate() {
        this.writeDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }

    public void updateDiary(String diaryContent, String weather, String diaryThema, String emotion, Music music, Gift gift) {
        this.diaryContent = diaryContent;
        this.weather = weather;
        this.diaryThema = diaryThema;
        this.emotion = emotion;
        this.music = music;
        this.gift = gift;
    }

    public void updateGift(Gift gift) {
        this.gift = gift;
    }

    public void openGift() {
        this.openGift = true;
    }

    public void closeGift() {
        this.openGift = false;
    }

    public void updateImg(String fileUrl) {
        this.diaryImg = fileUrl;
    }
}
