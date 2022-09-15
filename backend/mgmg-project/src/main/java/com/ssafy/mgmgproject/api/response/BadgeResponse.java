package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.AchievedBadge;
import com.ssafy.mgmgproject.db.entity.Badge;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("BadgeResponse")
public class BadgeResponse extends BaseResponseBody{


    @ApiModelProperty(name = "업적 이름")
    String badgeName;

    @ApiModelProperty(name = "업적 내용")
    String badgeContent;

    @ApiModelProperty(name = "업적 조건")
    String badgeCodition;

    @ApiModelProperty(name = "업적 획득 날짜")
    String achievedBadgeDate;

    @ApiModelProperty(name = "업적 획득 여부")
    boolean achievedBadgeFlag;

    public static BadgeResponse of(Badge badge, AchievedBadge achievedBadge, Integer statusCode, String message) {

        BadgeResponse res = new BadgeResponse();
        res.setBadgeName(badge.getBadgeName());
        res.setBadgeContent(badge.getBadgeContent());
        res.setBadgeCodition(badge.getBadgeCodition());
        res.setAchievedBadgeDate(achievedBadge==null? null : achievedBadge.getAchievedBadgeDate().toString());
        res.setAchievedBadgeFlag(achievedBadge!=null);
        res.setStatusCode(statusCode);
        res.setMessage(message);

        return res;
    }
}
