package com.ssafy.mgmgproject.api.controller;

import com.ssafy.mgmgproject.api.request.NoticeRequest;
import com.ssafy.mgmgproject.api.response.NoticeListMapping;
import com.ssafy.mgmgproject.api.response.NoticeListResponse;
import com.ssafy.mgmgproject.api.response.NoticeResponse;
import com.ssafy.mgmgproject.api.service.NoticeService;
import com.ssafy.mgmgproject.api.service.UserService;
import com.ssafy.mgmgproject.common.model.response.BaseResponseBody;
import com.ssafy.mgmgproject.db.entity.Notice;
import com.ssafy.mgmgproject.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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

    @Autowired
    private UserService userService;

    @PostMapping()
    @ApiOperation(value = "공지사항 작성", notes = "공지사항 제목과 내용을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 작성 성공", response = NoticeResponse.class),
            @ApiResponse(code = 401, message = "공지사항 작성 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> writeNotice(@ApiIgnore Authentication authentication, @RequestBody NoticeRequest noticeRequest) throws Exception{
        try {
            UserDetails userDetails = (UserDetails)authentication.getDetails();
            String userId = userDetails.getUsername();
            User user = userService.getByUserId(userId);

            if(!user.isAdmin()){
                return ResponseEntity.status(403).body(BaseResponseBody.of(403, "관리자만 접근 가능합니다."));
            }

            Notice notice = noticeService.writeNotice(noticeRequest);
            if(notice!=null) {
                return ResponseEntity.status(200).body(NoticeResponse.of(notice, 200, "공지사항이 등록되었습니다."));
            }else{
                return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 작성에 실패하셨습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 작성에 실패하셨습니다."));
        }
    }

    @GetMapping()
    @ApiOperation(value = "공지사항 목록 조회", notes = "공지사항 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 목록 조회 성공", response = NoticeListResponse.class),
            @ApiResponse(code = 401, message = "공지사항 목록 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> listNotice(
            @RequestParam(required = false) String keyword,
            @PageableDefault(sort = "noticeDate",  direction = Sort.Direction.DESC) Pageable pageable){
        Map<String,Object> result = noticeService.selectNoticeList(keyword,pageable);
        if(result==null){
            return ResponseEntity.status(401).body(BaseResponseBody.of(402, "공지사항 목록이 없습니다."));
        }else{
            return ResponseEntity.status(200).body(NoticeListResponse.of((List<NoticeListMapping>)result.get("page"),(int)result.get("totalPage"),(int)result.get("currentPage"),200, "공지사항 목록조회를 성공하였습니다."));
        }
    }

    @GetMapping("/{noticeNo}")
    @ApiOperation(value = "공지사항 상세조회", notes = "공지사항 상세정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 상세정보 조회 성공", response = NoticeResponse.class),
            @ApiResponse(code = 401, message = "공지사항 상세정보 조회 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 공지 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)})
    public ResponseEntity<? extends BaseResponseBody> detailNotice(@PathVariable long noticeNo){
        Notice notice = noticeService.getByNoticeNo(noticeNo);
        if (notice == null) return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 공지사항이 없습니다."));
        return ResponseEntity.status(200).body(NoticeResponse.of(notice, 200, "공지사항 상세조회를 성공하였습니다."));

    }

    @PutMapping("/{noticeNo}")
    @ApiOperation(value = "공지사항 수정", notes = "공지사항의 제목 또는 내용을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 수정 성공", response = NoticeResponse.class),
            @ApiResponse(code = 401, message = "공지사항 수정 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 공지 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> updateNotice(@ApiIgnore Authentication authentication, @PathVariable long noticeNo, @RequestBody NoticeRequest noticeRequest) throws Exception{
        UserDetails userDetails = (UserDetails)authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);

        if(!user.isAdmin()){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "관리자만 접근 가능합니다."));
        }
        try {
            Notice notice = noticeService.updateNotice(noticeNo,noticeRequest);
            if(notice!=null) {
                return ResponseEntity.status(200).body(NoticeResponse.of(notice, 200, "공지사항이 수정되었습니다."));
            }else{
                return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 공지사항이 없습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 수정에 실패하셨습니다."));
        }
    }

    @DeleteMapping("/{noticeNo}")
    @ApiOperation(value = "공지사항 삭제", notes = "공지사항 삭제")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 삭제 성공", response = BaseResponseBody.class),
            @ApiResponse(code = 401, message = "공지사항 삭제 실패", response = BaseResponseBody.class),
            @ApiResponse(code = 402, message = "해당 공지 없음", response = BaseResponseBody.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseResponseBody.class)
    })
    public ResponseEntity<? extends BaseResponseBody> deleteNotice(@ApiIgnore Authentication authentication, @PathVariable long noticeNo) throws Exception{
        UserDetails userDetails = (UserDetails)authentication.getDetails();
        String userId = userDetails.getUsername();
        User user = userService.getByUserId(userId);

        if(!user.isAdmin()){
            return ResponseEntity.status(403).body(BaseResponseBody.of(403, "관리자만 접근 가능합니다."));
        }
        boolean result;
        try {
            result = noticeService.deleteNotice(noticeNo);
            if(result){
                return ResponseEntity.status(200).body(BaseResponseBody.of(200, "공지사항이 삭제되었습니다."));
            }else{
                return ResponseEntity.status(402).body(BaseResponseBody.of(402, "해당하는 공지사항이 없습니다."));
            }
        } catch (Exception e) {
            return ResponseEntity.status(401).body(BaseResponseBody.of(401, "공지사항 삭제에 실패하셨습니다."));
        }
    }
}
