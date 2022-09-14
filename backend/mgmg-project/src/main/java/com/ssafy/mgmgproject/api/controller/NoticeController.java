package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.NoticeRequest;
import com.ssafy.mgmgproject.api.response.NoticeListMapping;
import com.ssafy.mgmgproject.api.response.NoticeListResponse;
import com.ssafy.mgmgproject.api.service.NoticeService;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @PostMapping()
    @ApiOperation(value = "공지사항 작성", notes = "공지사항 제목과 내용을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 작성 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "공지사항 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> writeNotice(@ApiIgnore Authentication authentication, @RequestBody NoticeRequest noticeRequest) throws Exception{
        // JWT 관리자 체크
        boolean result;
        try {
            result = noticeService.writeNotice(noticeRequest);
            if(result){
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공지사항이 등록되었습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 작성에 실패하셨습니다."));
        }
        return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 작성에 실패하셨습니다."));
    }

    @GetMapping()
    @ApiOperation(value = "공지사항 목록 조회", notes = "공지사항 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 목록 조회 성공", response = NoticeListResponse.class),
            @ApiResponse(code = 401, message = "공지사항 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listNotice(
            @ApiIgnore Authentication authentication,
            @RequestParam(required = false) String keyword,
            @PageableDefault(sort = "noticeDate",  direction = Sort.Direction.DESC) Pageable pageable){
        // JWT

        Map<String,Object> result = noticeService.selectNoticeList(keyword,pageable);
        if(result==null){
            return ResponseEntity.status(401).body(BaseResponseBody.of(402, "공지사항 목록이 없습니다."));
        }else{
            return ResponseEntity.status(200).body(NoticeListResponse.of((List<NoticeListMapping>)result.get("page"),(int)result.get("totalPage"),(int)result.get("currentPage"),200, "공지사항 목록조회를 성공하였습니다."));
        }
    }


}
