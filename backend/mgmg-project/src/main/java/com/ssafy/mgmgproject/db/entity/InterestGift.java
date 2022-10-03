package com.ssafy.mgmgproject.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "interest_gift")
public class InterestGift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interest_gift_no")
    private Long interestGiftNo;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_no")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_no")
    private Gift gift;

    @Column(name = "regist_date", updatable = false, length = 16)
    private String registDate;

    @PrePersist
    public void onPrePersist() {
        this.registDate = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
    }
}
