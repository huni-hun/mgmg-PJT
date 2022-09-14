package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.UserLoginPostReq;
import com.ssafy.mgmgproject.api.request.UserRegistPostReq;
import com.ssafy.mgmgproject.api.request.UserFindIdPostReq;
import com.ssafy.mgmgproject.api.request.UserFindPwPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"*"}, maxAge = 6000)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {

//    private final UserService userService;
    

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
