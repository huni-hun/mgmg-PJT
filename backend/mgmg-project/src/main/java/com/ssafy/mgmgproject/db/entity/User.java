package com.ssafy.mgmgproject.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "password", length = 16)
    private String password;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "birth")
    private String birth;

    @Column(name = "username", length = 16)
    private String username;

    @Column(name = "admin")
    private boolean admin;

    @Column(name = "diary_font")
    private int diaryFont;

    @Column(name = "diary_continue")
    private int diaryContinue;

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<InterestGift> interestGifts= new ArrayList<>();

    @JsonIgnore
    @Builder.Default
    @OneToMany(mappedBy = "user")
    private List<InterestMusic> interestMusics = new ArrayList<>();

}
