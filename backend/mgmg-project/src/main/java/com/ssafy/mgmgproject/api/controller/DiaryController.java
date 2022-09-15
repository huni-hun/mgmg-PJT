package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.DiaryRequest;
import com.ssafy.mgmgproject.api.response.DiaryListMapping;
import com.ssafy.mgmgproject.api.response.DiaryListResponse;
import com.ssafy.mgmgproject.api.response.DiaryResponse;
import com.ssafy.mgmgproject.api.service.DiaryService;
import com.ssafy.mgmgproject.common.auth.UserDetails;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Diary;
import com.ssafy.mgmgproject.db.entity.InterestGift;
import com.ssafy.mgmgproject.db.entity.InterestMusic;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
                                                                 @RequestBody @ApiParam(value = "일기 정보", required = true) DiaryRequest diaryRequest) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        Diary diary;
        try {
            diary = diaryService.writeDiary(userId, diaryRequest);
            if (diary == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 작성에 실패하였습니다."));
        }
        catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryResponse.of(diary, 200, "일기 작성에 성공하였습니다."));
    }

    @GetMapping("/month/{date}")
    @ApiOperation(value = "월별 일기 조회", notes = "월별 일기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 목록 조회 성공", response = DiaryListResponse.class),
            @ApiResponse(code = 401, message = "일기 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getDiaryMonthList(@ApiIgnore Authentication authentication, @PathVariable @ApiParam(value = "년도-월", required = true) String date) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        List<DiaryListMapping> diaries;
        try {
            diaries = diaryService.getDiaryMonthList(userNo, date);
        }
        catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 목록 조회에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryListResponse.of(diaries, 200, "일기 목록 조회에 성공하였습니다."));
    }

    @GetMapping("/detail/{diaryNo}")
    @ApiOperation(value = "일기 상세 조회", notes = "일기를 상제 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 상세 조회 성공", response = DiaryResponse.class),
            @ApiResponse(code = 401, message = "일기 상세 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getByDiaryNo (@PathVariable @ApiParam(value = "년도-월", required = true) Long diaryNo) throws Exception{
        Diary diary;
        try {
            diary = diaryService.getByDiaryNo(diaryNo);
        }
        catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 상세 조회에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryResponse.of(diary, 200, "일기 상세 조회에 성공하였습니다."));
    }

    @DeleteMapping("/{diaryNo}")
    @ApiOperation(value = "일기 삭제", notes = "일기를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "일기 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteDiary (@PathVariable @ApiParam(value = "년도-월", required = true) Long diaryNo)  throws Exception{
        int result = diaryService.deleteDiary(diaryNo);
        if(result == 1) return ResponseEntity.status(200).body(BaseResponseBody.of(200, "일기 삭제 성공하였습니다."));
        else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 삭제에 실패하였습니다."));
    }

    @PostMapping("interestmusic/{musicNo}")
    @ApiOperation(value = "관심 음악 추가", notes = "관심 음악을 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 음악 추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "관심 음악 추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> addInterestMusic(@ApiIgnore Authentication authentication,
                                                                       @PathVariable @ApiParam(value = "음악번호", required = true) Long musicNo) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        InterestMusic interestMusic;
        try {
            interestMusic = diaryService.addInterestMusic(userId, musicNo);
            if (interestMusic == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 음악 추가에 실패하였습니다."));
        }
        catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 음악 추가에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "관심 음악 추가에 성공하였습니다."));
    }

    @PostMapping("interestgift/{giftNo}")
    @ApiOperation(value = "관심 선물 추가", notes = "관심 선물을 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 선물 추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "관심 선물 추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> addInterestGift(@ApiIgnore Authentication authentication,
                                                                       @PathVariable @ApiParam(value = "선물번호", required = true) Long giftNo) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        InterestGift interestGift;
        try {
            interestGift = diaryService.addInterestGift(userId, giftNo);
            if (interestGift == null) return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 선물 추가에 실패하였습니다."));
        }
        catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 선물 추가에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "관심 선물 추가에 성공하였습니다."));
    }

}

