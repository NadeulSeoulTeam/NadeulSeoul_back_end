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
public class UserController {

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
    @ApiOperation(value = "구글 로그인 API 연동", notes = "redirectUri,clientId,scope 정보 보내기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/users/google")
    public ResponseEntity<Response> googleInfo(){
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();

        SocialLoginInfoDto socialLoginInfoDto = userInfoService.getSocialLoginInfo();

        response.setMessage("소셜 로그인 정보 반환 성공");
        response.setData(socialLoginInfoDto);
        response.setStatus(StatusEnum.OK);

        return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
    }

    @ApiOperation(value = "구글 로그인 API- authorizationCode", notes = "AuthorizationCode 받는 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/users/google/{authorizationCode}")
    public ResponseEntity<Response> GoogleLogin(@PathVariable("authorizationCode") String authorizationCode){
//        Response response = new Response();

        ResponseEntity<String> response = null;
        RestTemplate restTemplate = new RestTemplate();

        // According OAuth documentation we need to send the client id and secret key in the header for authentication
        String credentials = "javainuse:secret";
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("Authorization", "Basic " + encodedCredentials);

        HttpEntity<String> request = new HttpEntity<String>(headers);

        String access_token_url = "http://localhost:8080/oauth/token";
        access_token_url += "?code=" + authorizationCode;
        access_token_url += "&grant_type=authorization_code";
        access_token_url += "&redirect_uri=http://localhost:3000/";

        response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        System.out.println("Access Token Response ---------" + response.getBody());

        return null;
    }

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
