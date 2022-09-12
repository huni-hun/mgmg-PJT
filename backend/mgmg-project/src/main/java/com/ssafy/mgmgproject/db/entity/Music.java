package com.ssafy.mgmgproject.db.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "music")
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "music_no")
    private Long musicNo;

    @OneToOne(mappedBy = "music")
    @JsonIgnore
    private InterestMusic interestMusic;
}
