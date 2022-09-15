package com.ssafy.mgmgproject.api.response;

import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.MusicGenre;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("SearchMusicGenreGetResponse")
public class SearchMusicGenreGetResponse extends BaseResponseBody {
    @ApiModelProperty(name = "musicGenre")
    List<String> musicGenres;

    public static SearchMusicGenreGetResponse of(List<String> musicGenres, Integer statusCode, String message) {
        SearchMusicGenreGetResponse res = new SearchMusicGenreGetResponse();
        res.setStatusCode(statusCode);
        res.setMessage(message);
        res.setMusicGenres(musicGenres);
        return res;
    }
}
