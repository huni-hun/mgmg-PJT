package com.b306.mgmgproject.api.controller;

import com.b306.mgmgproject.api.response.BadgeListResponse;
import com.b306.mgmgproject.api.response.BadgeResponse;
import com.b306.mgmgproject.api.service.BadgeService;
import com.b306.mgmgproject.api.service.UserService;
import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.AchievedBadge;
import com.b306.mgmgproject.db.entity.Badge;
import com.b306.mgmgproject.db.entity.User;
import io.swagger.annotations.Api;
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

@Api(value = "업적 API")
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
    public ResponseEntity<? extends BaseResponseBody> listBadge(@ApiIgnore Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        List<Map<String, Object>> badgeListDtos = badgeService.selectBadgeList(user);
        if (badgeListDtos == null) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "업적 목록 조회에 실패했습니다."));
        } else {
            return ResponseEntity.status(200).body(BadgeListResponse.of(badgeListDtos, 200, "업적 목록 조회를 성공하였습니다."));
        }
    }

    @GetMapping("/achieve")
    @ApiOperation(value = "획득한 업적 목록 조회", notes = "획득한 업적 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "획득한 업적 목록 조회 성공", response = BadgeListResponse.class),
            @ApiResponse(code = 401, message = "획득한 업적 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listAchievedBadge(@ApiIgnore Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        List<Map<String, Object>> badgeListDtos = badgeService.selectAchievedBadgeList(user);
        if (badgeListDtos == null) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "획득한 업적 목록 조회에 실패했습니다."));
        } else {
            return ResponseEntity.status(200).body(BadgeListResponse.of(badgeListDtos, 200, "획득한 업적 목록 조회를 성공하였습니다."));
        }
    }

    @GetMapping("/{badgeNo}")
    @ApiOperation(value = "업적 상세 조회", notes = "업적을 상세 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "업적 상세 조회 성공", response = BadgeResponse.class),
            @ApiResponse(code = 401, message = "업적 상세 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> detailBadge(@ApiIgnore Authentication authentication, @PathVariable long badgeNo) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        AchievedBadge achievedBadge = badgeService.getByUserAndBadgeNo(user, badgeNo);
        Badge badge = badgeService.getByBadgeNo(badgeNo);
        if (badge == null) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "업적 상세 조회에 실패했습니다."));
        }
        return ResponseEntity.status(200).body(BadgeResponse.of(badge, achievedBadge, 200, "업적 상세 조회를 성공하였습니다."));
    }

}
