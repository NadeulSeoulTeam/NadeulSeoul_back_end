package com.alzal.nadeulseoulbackend.domain.mypage.controller;

import com.alzal.nadeulseoulbackend.domain.mypage.service.MypageService;
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
 * 마이페이지 정보, 팔로우 기능 API
 * */
@RestController
@RequestMapping("api/v1/mypage")
@Api(value = "MypageController")
public class MypageController {

    @Autowired
    MypageService mypageService;

    /*
    * 마이페이지 정보 가져오기
    * */
    @ApiOperation(value = "member_seq에 해당하는 마이페이지 정보를 반환한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "마이페이지 정보 가져오기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @GetMapping
    public ResponseEntity<Response> getMyInfo() {
        Long memberSeq = 1L; //임시 : 사용자 토큰으로 가져와야함

        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("마이페이지 정보 가져오기 성공");
        response.setData(mypageService.getMypageInfo(memberSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * member_seq 해당 멤버의 팔로잉 리스트 가져오기
     * */
    @ApiOperation(value = "member_seq에 해당하는 멤버의 팔로잉 리스트를 반환한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "팔로잉 리스트 가져오기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @GetMapping("/{member_seq}/followee")
    public ResponseEntity<Response> getFolloweeList(@PathVariable("member_seq") Long memberSeq) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("팔로잉 리스트 가져오기 성공");
        response.setData(mypageService.getFollowingList(memberSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * member_seq 해당 멤버의 팔로워 리스트 가져오기
     * */
    @ApiOperation(value = "member_seq에 해당하는 멤버의 팔로워 리스트를 반환한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "팔로워 리스트 가져오기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @GetMapping("/{member_seq}/follower")
    public ResponseEntity<Response> getFollowerList(@PathVariable("member_seq") Long memberSeq) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("팔로워 리스트 가져오기 성공");
        response.setData(mypageService.getFollowerList(memberSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 팔로우 하기
     * */
    @ApiOperation(value = "토큰에 저장된 사용자와 member_seq 해당 멤버를 팔로우한다.", response = Response.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "팔로우 하기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @PostMapping("/{member_seq}/follow")
    public ResponseEntity<Response> follow(@PathVariable("member_seq") Long followedMemberSeq) {
        Long memberSeq = 1L; //임시 : 사용자 토큰으로 가져와야함

        // 이미 팔로우된 사람인지 확인

        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        mypageService.insertFollow(memberSeq, followedMemberSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("팔로우 하기 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 언팔로우 하기
     * */
    @ApiOperation(value = "토큰에 저장된 사용자와 member_seq 해당 멤버를 언팔로우한다..", response = Response.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "언팔로우 하기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @DeleteMapping("/{member_seq}/unfollow")
    public ResponseEntity<Response> unfollow(@PathVariable("member_seq") Long followedMemberSeq) {
        Long memberSeq = 1L; //임시 : 사용자 토큰으로 가져와야함

        // 이미 언팔로우된사람인지 확인

        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        mypageService.deleteFollow(memberSeq, followedMemberSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("언팔로우 하기 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

}
