package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.response.BadgeListResponse;
import com.ssafy.mgmgproject.api.service.BadgeService;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.AchievedBadge;
import com.ssafy.mgmgproject.db.entity.Badge;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/badge")
public class BadgeController {

    @Autowired
    private BadgeService badgeService;

    @Autowired
    private UserService userService;

    @GetMapping()
    @ApiOperation(value = "업적 목록 조회", notes = "업적 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "업적 목록 조회 성공", response = BadgeListResponse.class),
            @ApiResponse(code = 401, message = "업적 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listBadge(
            @ApiIgnore Authentication authentication){

        UserDetails userDetails = (UserDetails)authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);

        List<Map<String,Object>> badgeListDtos = badgeService.selectBadgeList(user);

        if(badgeListDtos==null){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "업적 목록 조회에 실패했습니다."));
        }else{
            return ResponseEntity.status(200).body(BadgeListResponse.of(badgeListDtos,200, "업적 목록 조회를 성공하였습니다."));
        }
    }

}
