package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("BadgeListResponse")
public class BadgeListResponse extends BaseResponseBody {

    @ApiModelProperty(name = "업적 목록")
    List<Map<String, Object>> badges;

    public static BadgeListResponse of(List<Map<String, Object>> badges, Integer statusCode, String message) {
        BadgeListResponse res = new BadgeListResponse();
        res.setBadges(badges);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }

}
