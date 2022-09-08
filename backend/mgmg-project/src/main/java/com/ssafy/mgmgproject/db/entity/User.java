package com.ssafy.mgmgproject.db.entity;

import lombok.*;

import javax.persistence.*;

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

    @Column(name = "user_id", length = 16, unique = true)
    private String user_id;

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

    @Column(name = "diary-font")
    private int diaryFont;

    @Column(name = "diary_continue")
    private int diaryContinue;

}
