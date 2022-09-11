package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "achieved_badge")
public class AchievedBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="achieved_badge_no")
    private Long achievedBadgeNo;

    @Column(name = "achieved_badge_date")
    @Temporal(value = TemporalType.DATE)
    private String achievedBadgeDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "badge_no")
    private Badge badge;

    public void createAchievedBadge(User user,Badge badge){
        this.user = user;
        this.badge = badge;
    }

}
