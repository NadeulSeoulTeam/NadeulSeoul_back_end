package com.alzal.nadeulseoulbackend.domain.tag.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationResponseDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationSearchResponseDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.CurationService;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeDto;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeRequestDto;
import com.alzal.nadeulseoulbackend.domain.tag.service.CodeService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@Api(value = "TagController")
@RequestMapping("api/v1/tags")
public class TagController {

    @Autowired
    private CodeService codeService;

    @Autowired
    private CurationService curationService;

    @ApiOperation(value = "지역 태그", notes = "지역 태그 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "지역 태그 목록 조회 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/local")
    public ResponseEntity<Response> getLocalTagList() {
        List<CodeDto> localTagDtoList = codeService.findAllByGroup(1L);
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

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
        List<CodeDto> themeTagDtoList = codeService.findAllByGroup(2L);
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        response.setStatus(StatusEnum.OK);
        response.setMessage("테마 태그 목록 조회 성공");
        response.setData(themeTagDtoList);

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "태그 검색", notes = "태그 검색 목록 가져오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "태그 검색 목록 조회 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/search")
    public ResponseEntity<Response> getCurationListByTag(
            @RequestBody CodeRequestDto codeRequestDto,
            @PageableDefault(page = 0, size = 10, sort = "views", direction = Sort.Direction.DESC ) Pageable pageable
    ) {

        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Page<CurationSearchResponseDto> page = curationService.getCurationListByPageWithCode(codeRequestDto, pageable);
        response.setStatus(StatusEnum.OK);
        response.setMessage("태그 검색 목록 조회 성공");
        response.setData(page);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
