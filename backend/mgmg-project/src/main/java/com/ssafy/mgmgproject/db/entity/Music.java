package com.ssafy.mgmgproject.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "music")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_no")
    private Long musicNo;

    @Column(name = "music_name")
    private String musicName;

    @Column(name = "artist", length = 100)
    private String artist;

    @Column(name = "album_name", length = 180)
    private String albumName;

    @Column(name = "release_date", length = 10)
    private String releaseDate;

    @Column(name = "music_genre_name", length = 10)
    private String musicGenreName;

    @Column(name = "album_art", length = 180)
    private String albumArt;
}
