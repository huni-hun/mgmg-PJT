package com.b306.mgmgproject.api.response;

import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ApiModel("SearchMusicGenreGetResponse")
public class SearchMusicGenreGetResponse extends BaseResponseBody {
    @ApiModelProperty(name = "감정별 선호음악장르 리스트")
    Map<String,List<String>> musicTaste;

    public static SearchMusicGenreGetResponse of(Map<String,List<String>> musicTaste, Integer statusCode, String message) {
        SearchMusicGenreGetResponse res = new SearchMusicGenreGetResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMusicTaste(musicTaste);
        return res;
    }
}
