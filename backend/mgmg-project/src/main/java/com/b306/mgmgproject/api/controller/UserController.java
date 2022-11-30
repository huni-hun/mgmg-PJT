package com.b306.mgmgproject.api.controller;

import com.b306.mgmgproject.api.request.*;
import com.b306.mgmgproject.api.response.*;
import com.b306.mgmgproject.common.auth.UserDetails;
import com.b306.mgmgproject.common.util.JwtTokenUtil;
import com.b306.mgmgproject.api.dto.Mail;
import com.b306.mgmgproject.api.service.MailService;
import com.b306.mgmgproject.api.service.UserService;
import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.db.entity.User;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(value = "유저 API")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    MailService mailService;

    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> login(@RequestBody @ApiParam(value = "로그인 정보", required = true) UserLoginPostRequest loginInfo) {
        String userId = loginInfo.getUserId();
        String password = loginInfo.getPassword();
        User user = userService.getByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Not Registered"));
        }
        if (passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(200).body(UserLoginResponse.of(user, 200, "로그인에 성공하였습니다.", jwtTokenUtil.createAccessToken(userId), loginInfo.isAutoFlag() ? jwtTokenUtil.createRefreshToken(userId) : null));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "비밀번호를 다시 확인해주세요."));
    }

    @PostMapping("/autologin")
    @ApiOperation(value = "로그인 상태 유지", notes = "<strong>토큰</strong>을 통해 로그인 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginResponse.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> autologin(@RequestBody @ApiParam(value = "로그인 상태 유지 정보", required = true) UserAutoLoginPostRequest loginInfo) {
        String userId = loginInfo.getUserId();
        User user = userService.getByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(404).body(BaseResponseBody.of(404, "Not Registered"));
        }
        if (jwtTokenUtil.checkRefreshToken(userId, loginInfo.getRefreshToken())) {
            return ResponseEntity.status(200).body(UserLoginResponse.of(user, 200, "로그인 상태 유지에 성공하였습니다.", jwtTokenUtil.createAccessToken(userId), jwtTokenUtil.createRefreshToken(userId)));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "로그인 상태 유지에 실패했습니다. 로그인을 진행해주세요."));
    }

    @GetMapping("/findpw")
    @ApiOperation(value = "임시 비밀번호 전송", notes = "회원 정보를 입력하고 일치하면 임시 비밀번호를 메일로 전송한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 비밀번호 발급 성공", response = UserFindPwResponse.class),
            @ApiResponse(code = 401, message = "임시 비밀번호 발급 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> findPw(@RequestParam @ApiParam(value = "회원 이메일", required = true) String email,
                                                             @RequestParam @ApiParam(value = "회원 아이디", required = true) String userId) {
        User user = userService.getByUserIdAndEmail(userId, email);
        if (user == null) {
            return ResponseEntity.status(401).body(UserFindPwResponse.of(401, "입력한 정보를 다시 확인해주세요.", null));
        } else {
            String tmpPw = mailService.getTmpPassword();
            userService.updatePassword(user, tmpPw);
            Mail mail = mailService.createTempPwMail(tmpPw, user.getEmail());
            mailService.sendMail(mail);
            return ResponseEntity.status(200).body(UserFindPwGetResponse.of(200, "이메일 발송 성공", passwordEncoder.encode(user.getPassword())));
        }
    }

    @GetMapping("/findid")
    @ApiOperation(value = "아이디 찾기", notes = "회원의 이름과 이메일에 해당하는 회원 아이디를 찾는다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "아이디 찾기 성공", response = UserFindIdGetResponse.class),
            @ApiResponse(code = 401, message = "아이디 찾기 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> findId(@RequestParam @ApiParam(value = "회원 이름", required = true) String userName,
                                                             @RequestParam @ApiParam(value = "회원 이메일", required = true) String email) throws Exception {
        User user = userService.getByUserNameAndEmail(userName, email);
        if (user == null) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "고객님의 정보와 일치하는 아이디가 없습니다."));
        } else {
            return ResponseEntity.status(200).body(UserFindIdGetResponse.of(200, "아이디 찾기 성공", user.getUserId()));
        }
    }

    @GetMapping("/idcheck")
    @ApiOperation(value = "아이디 중복 검사", notes = "회원가입 시 아이디 중복을 체크한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용 가능", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "아이디 중복", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkId(@RequestParam @ApiParam(value = "회원 아이디", required = true) String userId) throws Exception {
        User user = userService.getByUserId(userId);
        if (user == null) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "사용 가능한 아이디입니다."));
        } else {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "사용 중인 아이디입니다."));
        }
    }

    @PostMapping("/email")
    @ApiOperation(value = "이메일 인증번호 전송", notes = "이메일 인증번호를 전송한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이에일 인증번호 발송 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "이메일 중복", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> authEmail(@RequestBody @ApiParam(value = "이메일 중복검사, 인증번호 발송", required = true) UserEmailPostRequest userEmailPostRequest) throws Exception {
        String email = userEmailPostRequest.getEmail();
        User user = userService.getByEmail(email);
        if (user != null) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "이메일 중복"));
        }
        Mail mail = mailService.createAuthMail(email);
        mailService.sendMail(mail);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "인증번호 발송 성공"));
    }

    @GetMapping("/emailcheck")
    @ApiOperation(value = "인증번호 확인", notes = "인증번호를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> checkAuthKey(@RequestParam @ApiParam(value = "회원 이메일", required = true) String email,
                                                                   @RequestParam @ApiParam(value = "인증번호", required = true) String emailNum) throws Exception {
        String checkEmail = mailService.checkAuthKey(emailNum);
        if (checkEmail == null || !checkEmail.equals(email)) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "인증번호가 올바르지 않습니다."));
        } else {
            mailService.deleteAuthKey(emailNum);
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "인증 성공"));
        }
    }

    @PostMapping("/regist")
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> register(@RequestBody @ApiParam(value = "회원가입 정보", required = true) @Valid UserRegistPostRequest userRegistPostRequest) {
        try {
            userService.createUser(userRegistPostRequest);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원가입에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원가입에 성공하였습니다."));
    }

    @PostMapping("/pwcheck")
    @ApiOperation(value = "비밀번호 확인", notes = "(token) 비밀번호 인증을 위해 로그인한 회원의 비밀번호와 일치하는 비밀번호를 입력한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> myPage(@ApiIgnore Authentication authentication,
                                                             @RequestBody @ApiParam(value = "회원가입 정보", required = true) @Valid UserPwCheckPostRequest userPwCheckPostRequest) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        if (passwordEncoder.matches(userPwCheckPostRequest.getPassword(), userDetails.getPassword())) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "비밀번호 인증 성공"));
        } else {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "비밀번호를 다시 확인해주세요."));
        }
    }

    @GetMapping("/mypage")
    @ApiOperation(value = "회원 본인 정보 조회", notes = "(token) 로그인한 회원 본인의 정보를 응답한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserMyPageResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<UserMyPageResponse> getUserInfo(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        return ResponseEntity.status(200).body(UserMyPageResponse.of(user, 200, "회원 정보 조회 성공"));
    }

    @PutMapping("/mypage")
    @ApiOperation(value = "회원 정보 수정", notes = "(token) 회원의 프로필 이미지, 이름 혹은 이메일을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 정보 수정 성공", response = UserMyPageResponse.class),
            @ApiResponse(code = 401, message = "회원 정보 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateUser(@ApiIgnore Authentication authentication,
                                                                 @RequestBody @ApiParam(value = "회원정보", required = true) @Valid UserUpdatePutRequest userUpdatePutRequest) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        int result = userService.updateUser(user, userUpdatePutRequest);
        if (result == 0) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "회원정보 수정에 실패하였습니다."));
        }
        User updatedUser = userService.getByUserId(userId);
        return ResponseEntity.status(200).body(UserMyPageResponse.of(updatedUser, 200, "회원정보가 수정되었습니다."));
    }

    @PutMapping("/mypage/password")
    @ApiOperation(value = "비밀번호 변경", notes = "(token) 회원의 비밀번호를 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "비밀번호 변경 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updatePw(@ApiIgnore Authentication authentication,
                                                               @RequestBody @ApiParam(value = "비밀번호 변경 정보", required = true) UserUpdatePwPutRequest userUpdatePwPutRequest) {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        userService.updatePassword(user, userUpdatePwPutRequest.getNewPassword());
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "비밀번호가 변경되었습니다."));
    }

    @PutMapping("/mypage/diaryfont")
    @ApiOperation(value = "폰트 수정", notes = "(token) 폰트를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "폰트 수정 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateFont(@ApiIgnore Authentication authentication,
                                                                 @RequestBody @ApiParam(value = "폰트수정", required = true) @Valid UserUpdateFontPutRequest userUpdateFontPutRequest) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        try {
            userService.updateDiaryFont(user, userUpdateFontPutRequest.getDiaryFont());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(BaseResponseBody.of(500, "오류 발생."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "폰트가 수정되었습니다."));
    }

    @DeleteMapping("/mypage")
    @ApiOperation(value = "회원 탈퇴", notes = "(token) 회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 탈퇴 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "회원 탈퇴 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteUser(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        userService.deleteUser(user);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "회원탈퇴가 완료되었습니다."));
    }

    @GetMapping("/mypage/music")
    @ApiOperation(value = "음악 취향 조회", notes = "(token) 회원가입시 조사한 음악 취향을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "취향 조회 성공", response = SearchMusicGenreGetResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> SearchMusicGenre(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        Map<String, List<String>> musicTaste = userService.searchMusicGenre(user);
        return ResponseEntity.status(200).body(SearchMusicGenreGetResponse.of(musicTaste, 200, "음악 취향이 조사되었습니다."));
    }

    @GetMapping("/mypage/gift")
    @ApiOperation(value = "선물 취향 조회", notes = "(token) 회원가입시 조사한 음악 취향을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "취향 조회 성공", response = SearchGiftCategoryGetResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> SearchGiftCategory(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        List<String> giftCategories = userService.searchGiftCategory(user);
        return ResponseEntity.status(200).body(SearchGiftCategoryGetResponse.of(giftCategories, user, 200, "선물 취향이 조회되었습니다."));
    }

    @PutMapping("/mypage/musicchange")
    @ApiOperation(value = "음악 취향 변경", notes = "(token) 회원가입시 조사한 음악 취향을 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "취향 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> ChangeMusicGenre(@ApiIgnore Authentication authentication,
                                                                       @RequestBody @ApiParam(value = "변경할 음악 취향", required = true) @Valid UserChangeMusicPutRequest userChangeMusicPutRequest) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        userService.changeMusicGenre(user, userChangeMusicPutRequest);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "음악 취향이 변경되었습니다."));
    }

    @PutMapping("/mypage/giftchange")
    @ApiOperation(value = "선물 취향 변경", notes = "(token) 회원가입시 조사한 선물 취향을 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "취향 변경 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> ChangeGiftCategory(@ApiIgnore Authentication authentication,
                                                                         @RequestBody @ApiParam(value = "변경할 선물 취향", required = true) @Valid UserChangeGiftPutRequest userChangeGiftPutRequest) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);
        userService.changeGiftCategory(user, userChangeGiftPutRequest);
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "선물 취향이 변경되었습니다."));
    }

}
