package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.dto.StatisticsDto;
import com.ssafy.mgmgproject.api.response.BadgeListResponse;
import com.ssafy.mgmgproject.api.response.StatisticsPercentListResponse;
import com.ssafy.mgmgproject.api.service.EmotionService;
import com.ssafy.mgmgproject.api.service.StatisticsService;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.auth.UserDetails;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Emotion;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private UserService userService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private EmotionService emotionService;

    @GetMapping("/percent")
    @ApiOperation(value = "기간별 통계 조회", notes = "기간별 통계를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기간별 통계 조회 성공", response = StatisticsPercentListResponse.class),
            @ApiResponse(code = 401, message = "기간별 통계 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "기간별 통계 조회 데이터 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listPercent(
            @ApiIgnore Authentication authentication, @RequestParam @ApiParam(value = "시작기간", required = true) String startDate,@RequestParam @ApiParam(value = "종료기간", required = true) String endDate) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        try {
            List<StatisticsDto> statisticsDtos =  statisticsService.selectStatisticsPercentList(userNo,startDate,endDate);
            if(statisticsDtos==null || statisticsDtos.size()==0 ){
                return ResponseEntity.status(402).body(BaseResponseBody.of(402, "통계 데이터가 없습니다."));
            }
            return ResponseEntity.status(402).body(BaseResponseBody.of(402, "통계 데이터가 없습니다."));
//            System.out.println(statisticsDtos.get(0).getEmotion());
//            Emotion emotion = emotionService.getByEmotionName(statisticsDtos.get(0).getEmotion());
//            return ResponseEntity.status(200).body(StatisticsPercentListResponse.of(statisticsDtos,emotion,200, "기간별 통계 조회를 성공하였습니다."));
        }catch (Exception e){
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "기간별 통계 조회에 실패했습니다."));
        }
    }

}
