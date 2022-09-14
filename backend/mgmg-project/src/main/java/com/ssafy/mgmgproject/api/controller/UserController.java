package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.UserLoginRequest;
import com.ssafy.mgmgproject.api.response.UserLoginResponse;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.common.util.JwtTokenUtil;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(value = "유저 API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginRequest loginInfo) {
        String userId = loginInfo.getUserId();
        String password = loginInfo.getPassword();
        User user = userService.getByUserId(userId);

        if (user == null) return ResponseEntity.status(404).body(BaseResponseBody.of(401, "Not Registered"));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.ok(UserLoginResponse.of(user, 200, "로그인에 성공하였습니다.", JwtTokenUtil.getToken(userId)));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "비밀번호를 다시 확인해주세요."));
    }
}
