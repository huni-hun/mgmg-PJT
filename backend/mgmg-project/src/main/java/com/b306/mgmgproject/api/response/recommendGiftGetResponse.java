package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.Gift;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("recommendGiftGetResponse")
public class recommendGiftGetResponse extends BaseResponseBody {

    @ApiModelProperty(name = "선물 번호")
    Long giftNo;

    @ApiModelProperty(name = "선물 이름")
    String giftName;

    @ApiModelProperty(name = "선물 가격")
    Long giftPrice;

    @ApiModelProperty(name = "선물 이미지")
    String giftImg;

    @ApiModelProperty(name = "선물 링크")
    String giftUrl;

    public static recommendGiftGetResponse of(Gift gift, Integer statusCode, String message) {
        recommendGiftGetResponse res = new recommendGiftGetResponse();
        res.setGiftNo(gift.getGiftNo());
        res.setGiftName(gift.getGiftName());
        res.setGiftPrice(gift.getGiftPrice());
        res.setGiftImg(gift.getGiftImg());
        res.setGiftUrl(gift.getGiftUrl());
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
