package com.ssafy.mgmgproject.db.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "gift")
public class Gift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gift_no")
    private Long giftNo;

    @Column(name = "gift_name", length = 100)
    private String giftName;

    @Column(name = "gift_price")
    private Long giftPrice;

    @Column(name = "gift_img")
    private String giftImg;

    @Column(name = "gift_url")
    private String giftUrl;
}
