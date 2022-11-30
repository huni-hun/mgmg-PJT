package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("SearchGiftCategoryGetResponse")
public class SearchGiftCategoryGetResponse extends BaseResponseBody {

    @ApiModelProperty(name = "giftCategory")
    List<String> giftCategories;

    @ApiModelProperty(name = "최저가", example = "10000")
    Long lowPrice;

    @ApiModelProperty(name = "최고가", example = "50000")
    Long highPrice;

    public static SearchGiftCategoryGetResponse of(List<String> giftCategories, User user, Integer statusCode, String message) {
        SearchGiftCategoryGetResponse res = new SearchGiftCategoryGetResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setGiftCategories(giftCategories);
        res.setLowPrice(user.getLowPrice());
        res.setHighPrice(user.getHighPrice());
        return res;
    }
}
