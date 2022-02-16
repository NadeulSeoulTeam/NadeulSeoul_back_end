package com.alzal.nadeulseoulbackend.domain.curations.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationSearchResponseDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationRequestDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationResponseDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.CurationService;
import com.alzal.nadeulseoulbackend.domain.curations.service.ImageService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@Api(value = "CurationController")
@RequestMapping("api/v1/auth/curations")
public class CurationController {

    @Autowired
    private CurationService curationService;

    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "큐레이션 목록", notes = "큐레이션 목록 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 목록 불러오기 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping()
    public ResponseEntity<Response> getCurationListPage(@PageableDefault(page = 0, size = 10, sort = "date", direction = Sort.Direction.DESC ) Pageable pageable) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        Page<CurationSearchResponseDto> curationSearchResponseDtoPage = curationService.getCurationListByPage(3L, pageable);
        response.setMessage("큐레이션 목록을 불러왔습니다.");
        response.setData(curationSearchResponseDtoPage);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @ApiOperation(value = "큐레이션 불러오기", notes = "큐레이션 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 상세 정보 불러오기가 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> getCuration(@PathVariable("id") final Long curationSeq) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 상세 정보 불러오기가 완료되었습니다.");
        CurationResponseDto curationResponseDto = null;
        List<Long> imageSeqList = new ArrayList<>();
        try {
            curationResponseDto = curationService.getCuration(curationSeq);
            imageSeqList = imageService.getImageByCuration(curationSeq);
        } catch (Exception e) {
            e.printStackTrace();
        }

        curationResponseDto.changeFileList(imageSeqList);
        response.setData(curationResponseDto);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "큐레이션 작성", notes = "큐레이션 작성하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 생성이 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping
    public ResponseEntity<Response> insertCuration(@RequestBody CurationRequestDto curationRequestDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        try {
            curationService.insertCuration(curationRequestDto);
            response.setStatus(StatusEnum.OK);
            response.setMessage("큐레이션 작성이 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "큐레이션 작성", notes = "큐레이션 작성하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 생성이 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/images")
    public ResponseEntity<Response> insertImageInCuration(final List<MultipartFile> fileList) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        try {
            curationService.insertCurationImage(fileList);
            response.setStatus(StatusEnum.OK);
            response.setMessage("이미지 업로드가 완료되었습니다.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }



    @ApiOperation(value = "큐레이션 수정", notes = "큐레이션 수정하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 수정이 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PutMapping
    public ResponseEntity<Response> updateCuration(final CurationRequestDto curationRequestDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

//        try {
//            curationService.updateCuration(curationRequestDto);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        response.setMessage("큐레이션 수정이 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "큐레이션 삭제", notes = "큐레이션 삭제하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 삭제가 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteCuration(@PathVariable("id") final Long curationSeq) {

        curationService.deleteCuration(curationSeq);

        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 삭제가 완료되었습니다.");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }
}
