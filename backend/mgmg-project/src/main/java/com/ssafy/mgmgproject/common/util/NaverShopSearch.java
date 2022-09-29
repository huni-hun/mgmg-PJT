package com.ssafy.mgmgproject.common.util;

import com.ssafy.mgmgproject.api.request.SearchItemRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

@Component
public class NaverShopSearch {

    private static String id;
    private static String secret;

    @Autowired
    public NaverShopSearch(@Value("${Naver.Client-Id}") String id, @Value("${Naver.Client-Secret}")String secret){
        this.id = id;
        this.secret = secret;
    }

    public String search(String query) {
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", id);
        headers.add("X-Naver-Client-Secret", secret);
        String body = "";

        HttpEntity<String> requestEntity = new HttpEntity<String>(body, headers);
        ResponseEntity<String> responseEntity = rest.exchange("https://openapi.naver.com/v1/search/shop.json?query=" + query + "&exclude=used:cbshop:rental&display=30", HttpMethod.GET, requestEntity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        int status = httpStatus.value();
        String response = responseEntity.getBody();

        return response;
    }

    public SearchItemRequest fromJSONtoItems(String result) {
        JSONObject rjson = new JSONObject(result);

        JSONArray items = rjson.getJSONArray("items");

        Random random = new Random();

        JSONObject itemJson;
        while (true){
            itemJson = (JSONObject) items.get(random.nextInt(items.length()));
            if(itemJson.getString("title").length()>100){
                continue;
            }else{
                break;
            }
        }

        SearchItemRequest item = new SearchItemRequest(itemJson);

        return item;
    }
}
