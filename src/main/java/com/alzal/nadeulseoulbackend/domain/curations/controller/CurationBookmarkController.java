package com.alzal.nadeulseoulbackend.domain.curations.controller;

import com.alzal.nadeulseoulbackend.domain.curations.service.CurationBookmarkService;
import com.alzal.nadeulseoulbackend.domain.users.service.UserInfoService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// http://localhost:8080/swagger-ui/index.html

/*
 * 큐레이션 스크랩 관련 API
 * */

@RestController
@Api(value = "BookmarkController")
@RequestMapping("api/v1/auth/curations/bookmarks")
public class CurationBookmarkController {

    @Autowired
    CurationBookmarkService curationBookmarkService;

    @Autowired
    UserInfoService userInfoService;

    /*
    * 큐레이션 스크랩하기
    * */
    @ApiOperation(value = "큐레이션을 스크랩한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 스크랩하기 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/{curation_seq}")
    public ResponseEntity<Response> insertCurationBookmark(@PathVariable("curation_seq") Long curationSeq) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        curationBookmarkService.insertCurationBookmark(userSeq, curationSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 스크랩하기 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 큐레이션 스크랩 취소하기
     * */
    @ApiOperation(value = "큐레이션 스크랩을 취소한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 스크랩 취소하기 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @DeleteMapping("/{curation_seq}")
    public ResponseEntity<Response> deleteCurationBookmark(@PathVariable("curation_seq") Long curationSeq) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        curationBookmarkService.deleteCurationBookmark(userSeq, curationSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 스크랩 취소하기 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 큐레이션 스크랩 여부
     * */
    @ApiOperation(value = "큐레이션 스크랩을 여부를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 스크랩 여부 확인 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/{curation_seq}")
    public ResponseEntity<Response> isCurationBookmark(@PathVariable("curation_seq") Long curationSeq) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 스크랩 여부 확인 성공");
        response.setData(curationBookmarkService.isCurationBookmark(userSeq, curationSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 스크랩한 큐레이션 나열
     * */
    @ApiOperation(value = "스크랩한 큐레이션 리스트를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 스크랩 리스트 조회 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping
    public ResponseEntity<Response> getCurationBookmarkList(@RequestParam("page") int page, @RequestParam("size") int size) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 스크랩 리스트 조회 성공");
        response.setData(curationBookmarkService.getCurationBookmarkList(userSeq, page, size));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }


}
