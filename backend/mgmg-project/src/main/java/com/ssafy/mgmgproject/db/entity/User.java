package com.ssafy.mgmgproject.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_id", length = 16)
    private String userId;

    @Column(name = "password", length = 60)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "birth")
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(name = "username", length = 16)
    private String userName;

    @Column(name = "gender", length = 3)
    private String gender;

    @Column(name = "admin")
    @ColumnDefault("false")
    private boolean admin;

    @Column(name = "diary_font")
    @ColumnDefault("0")
    private int diaryFont;

    @Column(name = "diary_continue")
    @ColumnDefault("0")
    private int diaryContinue;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<InterestGift> interestGifts= new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<InterestMusic> interestMusics = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private List<Notification> notifications = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private List<AchievedBadge> achievedBadges = new ArrayList<>();

    public void updatePw(String newPw) {
        this.password = newPw;
    }

    public void updateUser(String email, Date birth, String userName, String gender) {
        this.email = email;
        this.birth = birth;
        this.userName = userName;
        this.gender = gender;
    }
}
