package com.alzal.nadeulseoulbackend.domain.stores.controller;

import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreCartDto;
import com.alzal.nadeulseoulbackend.domain.stores.dto.StoreInfoDto;
import com.alzal.nadeulseoulbackend.domain.stores.service.StoreService;
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
 * 상가정보 관련 API
 * */

@RestController
@RequestMapping("api/v1")
@Api(value = "StoreController")
public class StoreController {

    @Autowired
    private StoreService storeService;

    @Autowired
    private UserInfoService userInfoService;

    /*
     *  찜한 장소 리스트 가져오기
     * */
    @ApiOperation(value = "user_seq가 찜한 장소 리스트를 가져온다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "찜한 장소 리스트 가져오기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @GetMapping("/stores/bookmarks/{user_seq}")
    public ResponseEntity<Response> getStoreBookmarkList(@PathVariable("user_seq") Long userSeq, @RequestParam("page") int page, @RequestParam("size") int size) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("찜한 장소 리스트 가져오기 성공");
        response.setData(storeService.getStoreBookmarkInfoDto(userSeq, page, size));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 장소 상세 정보 가져오기
     * */
    @ApiOperation(value = "장소 상세 정보를 가져온다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "장소 상세 정보 가져오기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @GetMapping("/stores/{store_seq}")
    public ResponseEntity<Response> getStoreInfo(@PathVariable("store_seq") Long storeSeq) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("장소 상세 정보 가져오기 성공");
        response.setData(storeService.getStoreInfoDto(storeSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 장소 찜하기
     * */
    @ApiOperation(value = "user가 store_seq에 해당하는 장소를 찜한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "장소 찜하기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @PostMapping("/auth/stores/bookmarks/{store_seq}")
    public ResponseEntity<Response> insertStoreBookmark(@PathVariable("store_seq") Long storeSeq, @RequestBody StoreInfoDto storeInfoDto) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        System.out.println("x값");
        System.out.println(storeInfoDto.getX());
        storeService.insertStoreBookmark(userSeq, storeSeq, storeInfoDto);
        response.setStatus(StatusEnum.OK);
        response.setMessage("장소 찜하기 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 장소 찜하기 취소
     * */
    @ApiOperation(value = "user가 store_seq에 해당하는 찜한 장소 취소한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "확인 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @DeleteMapping("/auth/stores/bookmarks/{store_seq}")
    public ResponseEntity<Response> deleteStoreBookmark(@PathVariable("store_seq") Long storeSeq) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        storeService.deleteStoreBookmark(userSeq, storeSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("장소 찜하기 취소 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 장소 찜한 여부 확인
     * */
    @ApiOperation(value = "user가 store_seq에 해당하는 장소를 찜했는지 확인한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "확인 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @GetMapping("/auth/stores/bookmarks/{store_seq}")
    public ResponseEntity<Response> getIsBookmark(@PathVariable("store_seq") Long storeSeq) {
        Long userSeq = userInfoService.getId();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("장소 찜한 여부 확인 성공");
        response.setData(storeService.getIsBookmark(userSeq, storeSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 찜한 장소 목록에서 선택한 순서대로 장소 정보 가져오기
     * */
    @ApiOperation(value = "찜한 장소 목록에서 선택한 순서대로 장소 정보 가져온다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "선택한 순서대로 장소 정보 가져오기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @PostMapping("/auth/stores/bookmarks/courses")
    public ResponseEntity<Response> getIsBookmark(@RequestBody StoreCartDto storeCartDto) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("선택한 순서대로 장소 정보 가져오기 성공");
        response.setData(storeService.getStoreInfoListInOrder(storeCartDto.getUserSeq(), storeCartDto.getStoreSeqList()));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }
}
