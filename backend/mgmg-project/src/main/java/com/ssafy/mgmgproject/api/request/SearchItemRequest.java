package com.ssafy.mgmgproject.api.request;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

@Setter
@Getter
public class SearchItemRequest {
        private String title;
        private String link;
        private String image;
        private Long lprice;

        public SearchItemRequest(JSONObject itemJson) {
            this.title = itemJson.getString("title");
            this.link = itemJson.getString("link");
            this.image = itemJson.getString("image");
            this.lprice = itemJson.getLong("lprice");
        }
}
