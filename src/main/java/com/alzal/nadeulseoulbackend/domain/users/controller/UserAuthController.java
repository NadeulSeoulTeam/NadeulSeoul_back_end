package com.alzal.nadeulseoulbackend.domain.users.controller;

import com.alzal.nadeulseoulbackend.domain.users.dto.AssignedUserDto;
import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfoDto;
import com.alzal.nadeulseoulbackend.domain.users.dto.SocialLoginInfoDto;
import com.alzal.nadeulseoulbackend.domain.users.service.UserInfoService;
import com.alzal.nadeulseoulbackend.global.auth.security.TokenProvider;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@Api(value = "UserController")
@RequestMapping("api/v1/auth")
public class UserAuthController {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserInfoService userInfoService;

//
//    @ApiOperation(value="소셜 로그인 버튼",notes="이미 로그인된 사용자 검사")
//    @ApiResponses({
//            @ApiResponse(code=200,message="로그인 가능"),
//            @ApiResponse(code=404,message = "page not found")
//    })
//    @GetMapping("/users/signin")
//    public ResponseEntity<Response> checkUserStatus(){
//        Response response = new Response();
//        ErrorResponse errorResponse = new ErrorResponse();
//        HttpHeaders httpHeaders = new HttpHeaders();
//        return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
//
//    }



    @ApiOperation(value = "로그아웃", notes = "redis내 토큰 제거 및 로그아웃")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/users/signout")
    public ResponseEntity<Response> signoutUser() {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();

        userInfoService.signoutUserfromRedis();

        response.setStatus(StatusEnum.OK);
        response.setMessage("로그아웃 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "닉네임,이모지 세팅")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 정보 수정"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PutMapping("/users/{id}")
    public ResponseEntity<Response> editUserInfo(@Valid @RequestBody @ApiParam(value = "회원가입 정보", required = true) SignupInfoDto signupInfo, @PathVariable String id) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        userInfoService.editUserInfo(signupInfo, id);

        response.setStatus(StatusEnum.OK);
        response.setMessage("회원 정보 수정 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "회원 가입", notes = "닉네임,이모지 세팅")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원 가입 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/users/signup")
    public ResponseEntity<Response> getExtraUserInfo(@Valid @RequestBody @ApiParam(value = "회원가입 정보", required = true) SignupInfoDto signupInfo) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        AssignedUserDto assignedUser = userInfoService.updateSignupInfo(signupInfo);

        response.setStatus(StatusEnum.OK);
        response.setMessage("회원 가입 성공");
        response.setData(assignedUser);
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "닉네임 중복 확인", notes = "닉네임 중복 확인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용가능한 닉네임 입니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/users/nickname")
    public ResponseEntity<Response> checkNickname(@Valid @RequestBody @ApiParam(value = "닉네임 ", required = true) HashMap<String, String> map) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();

        String nickname = map.get("nickname");
        System.out.println("nickname : " + nickname);
        userInfoService.checkNicknameDuplication(nickname);

        response.setStatus(StatusEnum.OK);
        response.setMessage("사용가능한 닉네임입니다.");

        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    @ApiOperation(value = "메인에서 사용자 정보 받기", notes = "메인에서 사용자 정보 받기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용가능한 닉네임 입니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/users")
    public ResponseEntity<Response> getUserInfo() {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();

        AssignedUserDto assignedUserDto = userInfoService.getAssignedUserInfo();

        response.setStatus(StatusEnum.OK);
        response.setMessage("유저 정보를 성공적으로 받아왔습니다.");
        response.setData(assignedUserDto);

        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }
}
