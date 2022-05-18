package com.alzal.nadeulseoulbackend.domain.mypage.controller;

import com.alzal.nadeulseoulbackend.domain.mypage.dto.FollowDto;
import com.alzal.nadeulseoulbackend.domain.mypage.dto.MypageInfoDto;
import com.alzal.nadeulseoulbackend.domain.mypage.service.MypageService;
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

import java.util.List;

// http://localhost:8080/swagger-ui/index.html

/*
 * 마이페이지 정보, 팔로우 기능 API
 * */
@RestController
@RequestMapping("api/v1")
@Api(value = "MypageController")
public class MypageController {

    @Autowired
    MypageService mypageService;

    @Autowired
    UserInfoService userInfoService;

    /*
     * 마이페이지 정보 가져오기
     * */
    @ApiOperation(value = "user_seq에 해당하는 마이페이지 정보를 반환한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "마이페이지 정보 가져오기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @GetMapping("/mypage/{user_seq}")
    public ResponseEntity<Response> getMyInfo(@PathVariable("user_seq") Long userSeq) {
        MypageInfoDto data = mypageService.getMypageInfo(userSeq);
        Response response = new Response("마이페이지 정보 가져오기 성공", data);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * user_seq 해당 멤버의 팔로잉 리스트 가져오기
     * */
    @ApiOperation(value = "user_seq에 해당하는 멤버의 팔로잉 리스트를 반환한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "팔로잉 리스트 가져오기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @GetMapping("/mypage/{user_seq}/followee")
    public ResponseEntity<Response> getFolloweeList(@PathVariable("user_seq") Long userSeq) {
        List<FollowDto> data = mypageService.getFollowingList(userSeq);
        Response response = new Response("팔로잉 리스트 가져오기 성공", data);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * user_seq 해당 멤버의 팔로워 리스트 가져오기
     * */
    @ApiOperation(value = "user_seq 해당하는 멤버의 팔로워 리스트를 반환한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "팔로워 리스트 가져오기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @GetMapping("/mypage/{user_seq}/follower")
    public ResponseEntity<Response> getFollowerList(@PathVariable("user_seq") Long userSeq) {
        List<FollowDto> data = mypageService.getFollowerList(userSeq);
        Response response = new Response("팔로워 리스트 가져오기 성공", data);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 팔로우 하기
     * */
    @ApiOperation(value = "토큰에 저장된 사용자와 user_seq 해당 멤버를 팔로우한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "팔로우 하기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @PostMapping("/auth/mypage/{user_seq}/follow")
    public ResponseEntity<Response> follow(@PathVariable("user_seq") Long followedUserSeq) {
        Long userSeq = userInfoService.getId();
        mypageService.insertFollow(userSeq, followedUserSeq);
        Response response = new Response("팔로우 하기 성공");
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 언팔로우 하기
     * */
    @ApiOperation(value = "토큰에 저장된 사용자와 user_seq 해당 멤버를 언팔로우한다..", response = Response.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "언팔로우 하기 성공"),
            @ApiResponse(code = 404, message = "page not found")})
    @DeleteMapping("/auth/mypage/{user_seq}/unfollow")
    public ResponseEntity<Response> unfollow(@PathVariable("user_seq") Long followedUserSeq) {
        Long userSeq = userInfoService.getId();
        mypageService.deleteFollow(userSeq, followedUserSeq);
        Response response = new Response("언팔로우 하기 성공");
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

}
