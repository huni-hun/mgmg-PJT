package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.request.UserLoginRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.api.response.DiaryListResponse;
import com.ssafy.mgmgproject.api.response.DiaryResponse;
import com.ssafy.mgmgproject.api.response.UserLoginResponse;
import com.ssafy.mgmgproject.api.service.DiaryService;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.auth.UserDetails;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.common.util.JwtTokenUtil;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "일기 API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @PostMapping()
    @ApiOperation(value = "일기 작성", notes = "일기를 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 작성 성공", response = DiaryResponse.class),
            @ApiResponse(code = 401, message = "일기 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> writeDiary(@ApiIgnore Authentication authentication,
                                                                 @RequestBody @ApiParam(value = "일기 정보", required = true) DiaryRequest diaryRequest)  throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        Diary diary;
        try {
            diary = diaryService.writeDiary(userId, diaryRequest);
            if (diary == null) return ResponseEntity.ok(BaseResponseBody.of(401, "일기 작성에 실패하였습니다."));
        }
        catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(401, "일기 작성에 실패하였습니다."));
        }
        return ResponseEntity.ok(DiaryResponse.of(diary, 200, "일기 작성에 성공하였습니다."));
    }

    @PostMapping("/{date}")
    @ApiOperation(value = "월별 일기 조회", notes = "월별 일기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 목록 조회 성공", response = DiaryListResponse.class),
            @ApiResponse(code = 401, message = "일기 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getDiaryMonthList(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "년도-월", required = true) String date)  throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        List<DiaryListMapping> diaries;
        try {
            diaries = diaryService.getDiaryMonthList(userNo, date);
        }
        catch (Exception e){
            return ResponseEntity.ok(BaseResponseBody.of(401, "일기 목록 조회에 실패하였습니다."));
        }
        return ResponseEntity.ok(DiaryListResponse.of(diaries, 200, "일기 목록 조회에 성공하였습니다."));
    }
}

