package com.alzal.nadeulseoulbackend.domain.statics.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationSearchResponseDto;
import com.alzal.nadeulseoulbackend.domain.statics.dto.NadeulerDto;
import com.alzal.nadeulseoulbackend.domain.statics.service.StaticsService;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreBookmarkInfoDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api(value = "StaticsController")
@RequestMapping("api/v1/statics")
public class StaticsController {

    @Autowired
    private StaticsService staticsService;

    @ApiOperation(value = "HOT한 코스 목록 불러오기", notes = "조회수로 정렬한 코스 목록 top 10 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "HOT한 코스 목록 불러오기가 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/courses")
    public ResponseEntity<Response> getHotCurationList() {
        List<CurationSearchResponseDto> curationSearchResponseDtoList = staticsService.getHotCurationList();
        Response response = new Response("HOT한 코스 목록 불러오기가 완료되었습니다.", curationSearchResponseDtoList);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "열정적인 나들러 목록 불러오기", notes = "큐레이션 생성 개수 많은 순으로 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "열정적인 나들러 불러오기가 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/nadeulers")
    public ResponseEntity<Response> getNadeulers() {
        HttpHeaders headers = new HttpHeaders();
        List<NadeulerDto> nadeulerDtoList = staticsService.getNadeulerList();
        Response response = new Response("열정적인 나들러 불러오기가 완료되었습니다.", nadeulerDtoList);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "나들러가 많이 찜한 장소 목록 불러오기", notes = "좋아요가 많은 순으로 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "나들러가 많이 찜한 장소 불러오기가 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/stores")
    public ResponseEntity<Response> getStoreBookmarkList() {
        List<StoreBookmarkInfoDto> storeBookmarkInfoDtoList = staticsService.getStoreBookmarkList();
         Response response = new Response("나들러가 많이 찜한 장소 불러오기가 완료되었습니다.", storeBookmarkInfoDtoList);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
