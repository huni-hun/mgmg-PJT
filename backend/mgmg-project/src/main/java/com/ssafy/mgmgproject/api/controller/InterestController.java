package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.response.InterestGiftResponse;
import com.ssafy.mgmgproject.api.response.InterestMusicResponse;
import com.ssafy.mgmgproject.api.response.SearchGiftCategoryGetResponse;
import com.ssafy.mgmgproject.api.response.SearchMusicGenreGetResponse;
import com.ssafy.mgmgproject.api.service.InterestService;
import com.ssafy.mgmgproject.api.service.InterestServiceImpl;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.auth.UserDetails;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.InterestGift;
import com.ssafy.mgmgproject.db.entity.InterestMusic;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@Api(value = "관심 API")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/interest")
public class InterestController {

    @Autowired
    InterestService interestService;

    @Autowired
    UserService userService;

    @GetMapping("/music")
    @ApiOperation(value = "관심 음악 조회", notes = "(token) 회원이 추가한 관심 음악들을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심음악 조회 성공", response = InterestMusicResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> SearchInterestMusic(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);

        List<InterestMusic> interestMusics = interestService.searchInterestMusic(user);
        return ResponseEntity.status(200).body(InterestMusicResponse.of(interestMusics, 200, "관심 음악이 조회되었습니다."));
    }

    @GetMapping("/gift")
    @ApiOperation(value = "관심 선물 조회", notes = "(token) 회원이 추가한 관심 선물들을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "관심선물 조회 성공", response = InterestGiftResponse.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> SearchInterestGift(@ApiIgnore Authentication authentication) throws Exception {
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        String userId = userDetails.getUser().getUserId();
        User user = userService.getByUserId(userId);

        List<InterestGift> interestGifts = interestService.searchInterestGift(user);
        return ResponseEntity.status(200).body(InterestGiftResponse.of(interestGifts, 200, "관심 선물이 조회되었습니다."));
    }
}
