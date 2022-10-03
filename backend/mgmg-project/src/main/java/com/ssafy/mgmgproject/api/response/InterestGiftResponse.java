package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.InterestGift;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("InterestGiftResponse")
public class InterestGiftResponse extends BaseResponseBody {

    @ApiModelProperty(name = "interestGift")
    List<InterestGift> gifts;

    public static InterestGiftResponse of(List<InterestGift> interestGifts, Integer statusCode, String message) {
        InterestGiftResponse res = new InterestGiftResponse();
        res.setGifts(interestGifts);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
