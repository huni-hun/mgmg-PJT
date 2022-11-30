package com.b306.mgmgproject.api.controller;

import com.b306.mgmgproject.api.request.DiaryRequest;
import com.b306.mgmgproject.api.request.SearchItemRequest;
import com.b306.mgmgproject.api.response.*;
import com.b306.mgmgproject.db.entity.*;
import com.b306.mgmgproject.api.request.DiaryUpdateRequest;
import com.b306.mgmgproject.api.service.BadgeService;
import com.b306.mgmgproject.api.service.DiaryService;
import com.b306.mgmgproject.api.service.UserService;
import com.b306.mgmgproject.common.auth.UserDetails;
import com.b306.mgmgproject.common.model.response.BaseResponseBody;
import com.b306.mgmgproject.common.util.NaverShopSearch;
import com.b306.mgmgproject.common.util.SpeechToText;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Api(value = "일기 API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryService diaryService;

    @Autowired
    BadgeService badgeService;

    @Autowired
    UserService userService;

    @Autowired
    NaverShopSearch naverShopSearch;

    @Autowired
    SpeechToText speechToText;

    @PostMapping("/speech")
    public ResponseEntity<? extends BaseResponseBody> STT(@RequestPart MultipartFile file) throws Exception {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        String result = speechToText.play(convFile);
        return ResponseEntity.status(200).body(SpeeachToTextResponse.of(result, 200, "변환 성공"));
    }

    @PostMapping()
    @ApiOperation(value = "일기 작성", notes = "일기를 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 작성 성공", response = DiaryResponse.class),
            @ApiResponse(code = 401, message = "일기 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> writeDiary(@ApiIgnore Authentication authentication,
                                                                 @RequestPart(required = false) @ApiParam(value = "일기 이미지") MultipartFile multipartFile,
                                                                 @RequestPart @ApiParam(value = "일기 정보", required = true) DiaryRequest diaryRequest) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        Long userNo = userDetails.getUser().getUserNo();
        Diary diary;
        try {
            diary = diaryService.writeDiary(userNo, multipartFile, diaryRequest);
            if (diary == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 작성에 실패하였습니다."));
            }
            badgeService.checkToGetBadge(userId, diary);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 작성에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryResponse.of(diary, 200, "일기 작성에 성공하였습니다."));
    }

    @GetMapping("/gift/{diaryNo}")
    @ApiOperation(value = "추천 선물 추가", notes = "네이버 api를 호출해서 사용자에게 선물을 추천한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "선물 추천 성공", response = recommendGiftGetResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> recommendGift(@ApiIgnore Authentication authentication,
                                                                    @PathVariable @ApiParam(value = "일기 번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);
        String userInfo = diaryService.getUserInfo(user.getBirth(), user.getGender(), userService.searchGiftCategory(user), user.getLowPrice(), user.getHighPrice());
        Gift gift;
        try {
            SearchItemRequest result = naverShopSearch.fromJSONtoItems(naverShopSearch.search(userInfo));
            gift = diaryService.writeRecommendGift(result, diaryNo, user);
            if (gift == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "선물추천에 실패하였습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "선물추천에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(recommendGiftGetResponse.of(gift, 200, "선물 추천에 성공하였습니다."));
    }

    @PutMapping("/{diaryNo}")
    @ApiOperation(value = "일기 수정", notes = "일기를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 수정 성공", response = DiaryResponse.class),
            @ApiResponse(code = 401, message = "일기 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> writeDiary(@ApiIgnore Authentication authentication,
                                                                 @PathVariable @ApiParam(value = "일기 번호", required = true) Long diaryNo,
                                                                 @RequestPart(required = false) @ApiParam(value = "일기 이미지") MultipartFile multipartFile,
                                                                 @RequestPart @ApiParam(value = "일기 정보", required = true) DiaryUpdateRequest diaryUpdateRequest) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        Diary diary;
        try {
            diary = diaryService.updateDiary(userNo, diaryNo, multipartFile, diaryUpdateRequest);
            if (diary == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 수정에 실패하였습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 수정에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryResponse.of(diary, 200, "일기 수정에 성공하였습니다."));
    }

    @GetMapping("/month/{date}")
    @ApiOperation(value = "월별 일기 조회", notes = "월별 일기를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 목록 조회 성공", response = DiaryListResponse.class),
            @ApiResponse(code = 401, message = "일기 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getDiaryMonthList(@ApiIgnore Authentication authentication,
                                                                        @PathVariable @ApiParam(value = "년도-월", required = true) String date) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        List<DiaryListMapping> diaries;
        try {
            diaries = diaryService.getDiaryMonthList(userNo, date);
        } catch (Exception e) {
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
    public ResponseEntity<? extends BaseResponseBody> getByDiaryNo(@ApiIgnore Authentication authentication,
                                                                   @PathVariable @ApiParam(value = "일기 번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        Diary diary;
        try {
            diary = diaryService.getByDiaryNo(userNo, diaryNo);
            if (diary == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 상세 조회에 실패하였습니다."));
            }
        } catch (Exception e) {
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
    public ResponseEntity<? extends BaseResponseBody> deleteDiary(@ApiIgnore Authentication authentication,
                                                                  @PathVariable @ApiParam(value = "일기번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        int result = diaryService.deleteDiary(userNo, diaryNo);
        if (result == 1) {
            return ResponseEntity.status(200).body(BaseResponseBody.of(200, "일기 삭제 성공하였습니다."));
        } else return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 삭제에 실패하였습니다."));
    }

    @PostMapping("/interestmusic/{musicNo}/{diaryNo}")
    @ApiOperation(value = "관심 음악 추가", notes = "관심 음악을 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 음악 추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "관심 음악 추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> addInterestMusic(@ApiIgnore Authentication authentication,
                                                                       @PathVariable @ApiParam(value = "음악번호", required = true) Long musicNo,
                                                                       @PathVariable @ApiParam(value = "일기번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        InterestMusic interestMusic;
        try {
            interestMusic = diaryService.addInterestMusic(userId, musicNo, diaryNo);
            if (interestMusic == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 음악 추가에 실패하였습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 음악 추가에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "관심 음악 추가에 성공하였습니다."));
    }

    @PostMapping("/interestgift/{giftNo}/{diaryNo}")
    @ApiOperation(value = "관심 선물 추가", notes = "관심 선물을 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심 선물 추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "관심 선물 추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> addInterestGift(@ApiIgnore Authentication authentication,
                                                                      @PathVariable @ApiParam(value = "선물번호", required = true) Long giftNo,
                                                                      @PathVariable @ApiParam(value = "일기번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUsername();
        InterestGift interestGift;
        try {
            interestGift = diaryService.addInterestGift(userId, giftNo, diaryNo);
            if (interestGift == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 선물 추가에 실패하였습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "관심 선물 추가에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "관심 선물 추가에 성공하였습니다."));
    }

    @PostMapping("/badmusic/{musicNo}")
    @ApiOperation(value = "음악 비추천 리스트 추가", notes = "추천받고 싶지 않은 음악을 추가한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음악 비추천 리스트 추가 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "음악 비추천 리스트 추가 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> addBadMusic(@ApiIgnore Authentication authentication,
                                                                  @PathVariable @ApiParam(value = "음악번호", required = true) Long musicNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        BadMusic badMusic;
        try {
            badMusic = diaryService.addBadMusic(userNo, musicNo);
            if (badMusic == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "음악 비추천 리스트 추가에 실패하였습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "음악 비추천 리스트 추가에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "음악 비추천 리스트 추가에 성공하였습니다."));
    }

    @DeleteMapping("/badmusic/{musicNo}")
    @ApiOperation(value = "음악 비추천 리스트에서 삭제", notes = "추천받고 싶지 않은 음악을 취소한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "음악 비추천 리스트에서 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "음악 비추천 리스트에서 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> cancelBadMusic(@ApiIgnore Authentication authentication,
                                                                     @PathVariable @ApiParam(value = "음악번호", required = true) Long musicNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        int result;
        try {
            result = diaryService.deleteBadMusic(userNo, musicNo);
            if (result == 0) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "음악 비추천 리스트에서 삭제를 실패하였습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "음악 비추천 리스트에서 삭제를 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(BaseResponseBody.of(200, "음악 비추천 리스트에서 삭제를 성공하였습니다."));
    }

    @GetMapping("/detail/{diaryNo}/music")
    @ApiOperation(value = "일기 상세 조회시 노래 추천", notes = "일기를 상제 조회시 노래를 추천한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 상세 조회시 노래 추천 성공", response = DiaryMusicResponse.class),
            @ApiResponse(code = 401, message = "일기 상세 조회시 노래 추천 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getMusic(@ApiIgnore Authentication authentication,
                                                               @PathVariable @ApiParam(value = "일기 번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        Diary diary;
        String checkMusic;
        try {
            diary = diaryService.getByDiaryNo(userNo, diaryNo);
            if (diary == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 상세 조회시 노래 추천에 실패하였습니다."));
            }
            checkMusic = diaryService.checkMusic(userNo, diary.getMusic().getMusicNo());
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 상세 조회시 노래 추천에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryMusicResponse.of(diary, checkMusic, 200, "일기 상세 조회시 노래 추천에 성공하였습니다."));
    }

    @GetMapping("/detail/{diaryNo}/gift")
    @ApiOperation(value = "일기 상세 조회시 선물 추천", notes = "일기를 상제 조회시 선물을 추천한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "일기 상세 조회시 선물 추천 성공", response = DiaryGiftResponse.class),
            @ApiResponse(code = 401, message = "일기 상세 조회시 선물 추천 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> getGift(@ApiIgnore Authentication authentication,
                                                              @PathVariable @ApiParam(value = "일기 번호", required = true) Long diaryNo) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        Diary diary;
        String checkGift = null;
        try {
            diary = diaryService.getByDiaryNo(userNo, diaryNo);
            if (diary == null) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 상세 조회시 선물 추천에 실패하였습니다."));
            }
            if (diary.getGift() != null) {
                checkGift = diaryService.checkGift(userNo, diary.getGift().getGiftNo());
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "일기 상세 조회시 선물 추천에 실패하였습니다."));
        }
        return ResponseEntity.status(200).body(DiaryGiftResponse.of(diary, checkGift, 200, "일기 상세 조회시 선물 추천에 성공하였습니다."));
    }

}

