package com.alzal.nadeulseoulbackend.domain.users.controller;

import com.alzal.nadeulseoulbackend.domain.users.dto.SignupInfo;
import com.alzal.nadeulseoulbackend.domain.users.service.UserInfoService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.alzal.nadeulseoulbackend.global.security.TokenProvider;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Api(value = "UserController")
@RequestMapping("api/v1")
public class UserController {

    @Autowired
    private TokenProvider tokenProvider;
    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value="회원 가입",notes="닉네임,이모지 세팅")
    @ApiResponses({
            @ApiResponse(code=200,message="회원 가입 성공"),
            @ApiResponse(code=404,message = "page not found")
    })
    @PostMapping("/member/signup")
    public ResponseEntity<Response> getExtraUserInfo(@Valid @RequestBody @ApiParam(value = "회원가입 정보",required = true) SignupInfo signupInfo){
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();

        userInfoService.updateSignupInfo(signupInfo);

        response.setStatus(StatusEnum.OK);
        response.setMessage("회원 가입 성공");

        return new ResponseEntity<Response>(response,httpHeaders,HttpStatus.OK);
    }

}
