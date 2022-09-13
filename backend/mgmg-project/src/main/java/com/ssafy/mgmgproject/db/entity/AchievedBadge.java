package com.ssafy.mgmgproject.db.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "achieved_badge")
@EntityListeners(AuditingEntityListener.class)
public class AchievedBadge {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="achieved_badge_no")
    private Long achievedBadgeNo;

    @Column(name = "achieved_badge_date")
    @CreatedDate
    private LocalDate achievedBadgeDate;

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
