package com.alzal.nadeulseoulbackend.domain.users.controller;

import com.alzal.nadeulseoulbackend.domain.users.dto.SocialLoginInfoDto;
import com.alzal.nadeulseoulbackend.domain.users.service.UserInfoService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import com.nimbusds.oauth2.sdk.http.HTTPRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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

    @ApiOperation(value = "구글 로그인 API- authorizationCode", notes = "AuthorizationCode 받는 요청")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/users/google")
    public ResponseEntity<Response> GoogleLogin(@RequestParam("code") String authorizationCode){
//        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();
        Response responseData = null;
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

        ResponseEntity<String> responseFromGoogle = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);

        System.out.println("Access Token Response ---------" + responseFromGoogle.getBody());
        responseData.setStatus(StatusEnum.OK);
        responseData.setMessage("accessToken 전송");
        responseData.setData(responseFromGoogle.getBody());
        return new ResponseEntity<Response>(responseData,httpHeaders, HttpStatus.OK);
    }

}
