package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.InterestMusic;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("InterestMusicResponse")
public class InterestMusicResponse extends BaseResponseBody {

    @ApiModelProperty(name = "interestMusic")
    List<InterestMusic> musics;

    public static InterestMusicResponse of(List<InterestMusic> interestMusics, Integer statusCode, String message) {
        InterestMusicResponse res = new InterestMusicResponse();
        res.setMusics(interestMusics);
        res.setStatusCode(statusCode);
        res.setMessage(message);
        return res;
    }
}
