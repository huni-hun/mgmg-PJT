package com.ssafy.mgmgproject.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "badge")
public class Badge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "badge_no")
    private Long badgeNo;

    @Column(name = "badge_name", length = 30, unique = true)
    private String badgeName;

    @Column(name = "badge_content", length = 100)
    private String badgeContent;

    @Column(name = "badge_condition", length = 20)
    private String badgeCondition;

    @OneToMany(mappedBy = "badge", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonIgnore
    private List<AchievedBadge> achievedBadges = new ArrayList<>();

}
