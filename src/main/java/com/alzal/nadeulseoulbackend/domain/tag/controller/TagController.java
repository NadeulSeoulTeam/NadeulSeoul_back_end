package com.alzal.nadeulseoulbackend.domain.tag.controller;

import java.nio.charset.Charset;
import java.util.List;
import com.alzal.nadeulseoulbackend.domain.tag.dto.LocalTagDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.ThemeTagDto;
import com.alzal.nadeulseoulbackend.domain.tag.service.TagService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "TagController")
@RequestMapping("api/v1/tags")
public class TagController {

    @Autowired
    private TagService tagService;

    @ApiOperation(value = "지역 태그", notes = "지역 태그 목록 가져오기")
    @ApiResponses({
                    @ApiResponse(code = 200, message = "지역 태그 목록 조회 성공"),
                    @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/local")
    public ResponseEntity<Response> getLocalTagList() {
        List<LocalTagDto> localTagDtoList = tagService.findAllLocalTag();
        System.out.println(localTagDtoList.size());
        Response response = new Response();
        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        response.setStatus(StatusEnum.OK);
        response.setMessage("지역 태그 목록 조회 성공");
        response.setData(localTagDtoList);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "테마 태그", notes = "테마 태그 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "테마 태그 목록 조회 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/theme")
    public ResponseEntity<Response> getThemeTagList() {
        List<ThemeTagDto> themeTagDtoList = tagService.findAllThemeTag();
        System.out.println(themeTagDtoList.size());
        Response response = new Response();
        HttpHeaders headers= new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        response.setStatus(StatusEnum.OK);
        response.setMessage("테마 태그 목록 조회 성공");
        response.setData(themeTagDtoList);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
