package com.alzal.nadeulseoulbackend.domain.users.controller;

import com.alzal.nadeulseoulbackend.domain.users.dto.SocialLoginInfoDto;
import com.alzal.nadeulseoulbackend.domain.users.service.UserInfoService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "UserController")
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {
    final private UserInfoService userInfoService;
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

        return new ResponseEntity<Response>(response,httpHeaders, HttpStatus.OK);
    }
}
