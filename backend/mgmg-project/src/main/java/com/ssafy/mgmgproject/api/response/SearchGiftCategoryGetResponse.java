package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
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

    public static SearchGiftCategoryGetResponse of(List<String> giftCategories, Integer statusCode, String message) {
        SearchGiftCategoryGetResponse res = new SearchGiftCategoryGetResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setGiftCategories(giftCategories);
        return res;
    }
}
