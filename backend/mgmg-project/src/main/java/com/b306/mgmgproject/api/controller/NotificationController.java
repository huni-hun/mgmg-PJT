package com.b306.mgmgproject.api.controller;

import com.b306.mgmgproject.api.response.NewNotificationResponse;
import com.b306.mgmgproject.api.response.NotificationListMapping;
import com.b306.mgmgproject.api.response.NotificationListResponse;
import com.b306.mgmgproject.api.response.*;
import com.b306.mgmgproject.api.service.NotificationService;
import com.b306.mgmgproject.api.service.UserService;
import com.b306.mgmgproject.common.model.response.BaseResponseBody;
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

@Api(value = "알림 API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/check")
    @ApiOperation(value = "신규 알림 확인", notes = "신규 알림 여부를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "신규 알림 여부 확인 성공", response = NewNotificationResponse.class),
            @ApiResponse(code = 401, message = "신규 알림 여부 확인 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkNewNotification(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        try {
            User user = userService.getByUserId(userId);
            boolean result = notificationService.checkNewNotification(user);
            return ResponseEntity.status(200).body(NewNotificationResponse.of(result, 200, "신규 알림 여부 확인을 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "신규 알림 여부 확인을 실패하였습니다."));
        }
    }

    @GetMapping()
    @ApiOperation(value = "알림 목록 조회", notes = "알림 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "알림 목록 조회 성공", response = NotificationListResponse.class),
            @ApiResponse(code = 401, message = "알림 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listNotification(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        try {
            User user = userService.getByUserId(userId);
            List<NotificationListMapping> notifications = notificationService.selectAndDeleteNotificationList(user);
            return ResponseEntity.status(200).body(NotificationListResponse.of(notifications, 200, "알림 목록 조회를 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "알림 목록 조회에 실패하였습니다."));
        }
    }
}
