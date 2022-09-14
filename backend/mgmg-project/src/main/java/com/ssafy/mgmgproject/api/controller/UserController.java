package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.UserFindIdPostReq;
import com.ssafy.mgmgproject.api.request.UserFindPwPostReq;
import com.ssafy.mgmgproject.api.request.UserLoginRequest;
import com.ssafy.mgmgproject.api.request.UserRegistPostReq;
import com.ssafy.mgmgproject.api.response.UserLoginResponse;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.common.util.JwtTokenUtil;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(value = "유저 API")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
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

    @PostMapping("/findpw")
    public ResponseEntity<?> findPw(@RequestBody UserFindPwPostReq userInfo) {
        String userId = userInfo.getUserId();
        String userPw = userInfo.getEmail();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/findid")
    public ResponseEntity<?> findId(@RequestBody UserFindIdPostReq userInfo) {
        String userName = userInfo.getUserName();
        String userPw = userInfo.getEmail();

        return new ResponseEntity<>()
    }

    @PostMapping("/idcheck")
    public ResponseEntity<?> idCheck(@RequestBody String userId) {

        return new ResponseEntity<>()
    }

    @PostMapping("/email")
    public ResponseEntity<?> email(@RequestBody String email) {

        return new ResponseEntity<>()
    }

    @PostMapping("/emailCheck")
    public ResponseEntity<?> emailCheck(@RequestBody String emailNum) {

        return new ResponseEntity<>()
    }

    @PostMapping("/regist")
    public ResponseEntity<?> register(@RequestBody @Valid UserRegistPostReq userRegistPostReq) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
