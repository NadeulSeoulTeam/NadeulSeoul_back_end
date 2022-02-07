package com.alzal.nadeulseoulbackend.domain.curations.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CurationDtoFirst;
import com.alzal.nadeulseoulbackend.domain.curations.dto.FileDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.CurationService;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Api(value = "CurationController")
@RequestMapping("api/v1/curations")
public class CurationController {

    @Autowired
    private CurationService curationService;

    @ApiOperation(value = "큐레이션 작성", notes = "큐레이션 작성하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 작성이 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping
    public ResponseEntity<Response> insertCuration(final CurationDto curationDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        response.setMessage("큐레이션 작성이 완료되었습니다.");

        try {
            curationService.insertCuration(curationDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @ApiOperation(value = "큐레이션 수정", notes = "큐레이션 수정하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "큐레이션 수정이 완료되었습니다."),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PutMapping
    public ResponseEntity<Response> updateCuration(final CurationDto curationDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        response.setMessage("큐레이션 수정이 완료되었습니다.");

        try {
            curationService.updateCuration(curationDto);
        } catch (IOException e) {
            e.printStackTrace();
        }

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

    @PostMapping("/test")
    public ResponseEntity<Response> testCuration(FileDto fileDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        System.out.println("통신 확인");
        System.out.println(fileDto.getImg());
        for(MultipartFile multipartFile : fileDto.getImg()) {
            System.out.println(multipartFile.getOriginalFilename());
        }


        response.setStatus(StatusEnum.OK);
        response.setMessage("큐레이션 생성이 완료되었습니다.");

        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


}
