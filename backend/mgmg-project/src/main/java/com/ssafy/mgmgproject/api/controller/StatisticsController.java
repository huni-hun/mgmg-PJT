package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.dto.StatisticsEmotionDto;
import com.ssafy.mgmgproject.api.response.StatisticsDayListResponse;
import com.ssafy.mgmgproject.api.response.StatisticsDayResponse;
import com.ssafy.mgmgproject.api.response.StatisticsPercentListResponse;
import com.ssafy.mgmgproject.api.service.EmotionService;
import com.ssafy.mgmgproject.api.service.StatisticsService;
import com.ssafy.mgmgproject.common.auth.UserDetails;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Emotion;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@Api(value = "통계 API")
@CrossOrigin("*")
@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private EmotionService emotionService;

    @GetMapping("/percent")
    @ApiOperation(value = "기간별 통계 조회", notes = "기간별 통계를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기간별 통계 조회 성공", response = StatisticsPercentListResponse.class),
            @ApiResponse(code = 401, message = "기간별 통계 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 기간동안 쓴 일기 데이터 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listPercent(@ApiIgnore Authentication authentication,
                                                                  @RequestParam @ApiParam(value = "시작기간", required = true) String startDate,
                                                                  @RequestParam @ApiParam(value = "종료기간", required = true) String endDate) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        try {
            List<StatisticsEmotionDto> statisticsDtos =  statisticsService.selectStatisticsPercentList(userNo,startDate,endDate);
            if(statisticsDtos==null || statisticsDtos.size()==0 ){
                return ResponseEntity.status(402).body(BaseResponseBody.of(402, "이 기간동안 쓴 일기가 없습니다."));
            }
            Emotion emotion = emotionService.getByEmotionName(statisticsDtos.get(0).getEmotion());
            return ResponseEntity.status(200).body(StatisticsPercentListResponse.of(statisticsDtos,emotion,200, "기간별 통계 조회를 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "기간별 통계 조회에 실패했습니다."));
        }
    }

    @GetMapping("/day")
    @ApiOperation(value = "요일별 통계 조회", notes = "요일별 통계를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요일별 통계 조회 성공", response = StatisticsDayListResponse.class),
            @ApiResponse(code = 401, message = "요일별 통계 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listDay(@ApiIgnore Authentication authentication) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        try {
            Map<String,List<Integer>> statisticsDtos =  statisticsService.selectStatisticsDayList(userNo);
            if(statisticsDtos==null || statisticsDtos.size()==0 ) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "요일별 통계 조회에 실패했습니다."));
            }
            return ResponseEntity.status(200).body(StatisticsDayListResponse.of(statisticsDtos,200, "요일별 통계 조회를 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "요일별 통계 조회에 실패했습니다."));
        }
    }

    @GetMapping("/day/{day}")
    @ApiOperation(value = "요일별 통계에서 요일 선택 조회", notes = "요일 중 가장 많이 느낀 감정을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "요일 선택 조회 성공", response = StatisticsDayResponse.class),
            @ApiResponse(code = 401, message = "요일 선택 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> selectDay(@ApiIgnore Authentication authentication, @PathVariable String day) throws Exception{
        UserDetails userDetails = (UserDetails) authentication.getDetails();
        Long userNo = userDetails.getUser().getUserNo();
        try {
            StatisticsEmotionDto statisticsDto = statisticsService.selectStatisticsDay(userNo,day);
            if(statisticsDto==null ) {
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "요일 선택 조회에 실패했습니다."));
            }
            return ResponseEntity.status(200).body(StatisticsDayResponse.of(day,statisticsDto,200, "요일 선택 조회를 성공하였습니다."));
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "요일 선택 조회에 실패했습니다."));
        }
    }

}
