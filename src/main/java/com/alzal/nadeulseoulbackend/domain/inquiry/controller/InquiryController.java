package com.alzal.nadeulseoulbackend.domain.inquiry.controller;

import com.alzal.nadeulseoulbackend.domain.inquiry.dto.*;
import com.alzal.nadeulseoulbackend.domain.inquiry.entity.Inquiry;
import com.alzal.nadeulseoulbackend.domain.inquiry.service.InquiryService;
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

import java.util.List;

// http://localhost:8080/swagger-ui/index.html

/*
* 1:1 문의사항 관련 API
* */

@RestController
@RequestMapping("api/v1/inquiries")
@Api(value = "InquiryController")
public class InquiryController {

    @Autowired
    private InquiryService inquiryService;

    /*
    * 문의사항 목록 가져오기
    * */
    @ApiOperation(value = "user_seq에 해당하는 문의사항 목록 반환한다.", response = List.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "문의 사항 목록가져오기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @GetMapping("/questions/list/{user_seq}")
    public ResponseEntity<Response> getInquiryList(@PathVariable("user_seq") Long userSeq) {
        // 사용자 정보 토큰으로 가져옴 -> url 변경하고 pathvariable 없애기!!!!!!!! (실험하려고 작성)
//        Member member = new Member();
//        Long userSeq = member.getMemberSeq();
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("문의 사항 목록가져오기 성공");
        response.setData(inquiryService.getInquiryList(userSeq)); //data 타입 확인해보기
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 문의사항 내용 가져오기
     * */
    @ApiOperation(value = "question_seq에 해당하는 문의사항 세부내용을 반환한다.", response = Inquiry.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "문의 사항 가져오기 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @GetMapping("/questions/{question_seq}")
    public ResponseEntity<Response> getInquiry(@PathVariable("question_seq") Long questionSeq) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        response.setStatus(StatusEnum.OK);
        response.setMessage("문의 사항 가져오기 성공");
        response.setData(inquiryService.getInquiry(questionSeq));
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
    * 문의사항 작성하기
   * */
    @ApiOperation(value = "문의 사항을 작성한다.", response = String.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "문의 사항 작성 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @PostMapping("/questions")
    public ResponseEntity<Response> insertInquiry(@RequestBody InquiryInfoDto inquiryInfoDto) {
        //사용자 토큰 가져오기
        Long userSeq = 1L;
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        inquiryService.insertInquiry(userSeq, inquiryInfoDto);
        response.setStatus(StatusEnum.OK);
        response.setMessage("문의 사항 작성 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 문의사항 수정하기
     * */
    @ApiOperation(value = "문의 사항을 수정한다.", response = String.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "문의 사항 수정 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @PutMapping("/questions/{question_seq}")
    public ResponseEntity<Response> updateInquiry(@PathVariable("question_seq") Long questionSeq, @RequestBody InquiryInfoDto inquiryInfoDto) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        inquiryService.updateInquiry(questionSeq, inquiryInfoDto);
        response.setStatus(StatusEnum.OK);
        response.setMessage("문의 사항 수정 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 문의사항 삭제하기 (실제 db에서 삭제하지 않고 숨김처리)
     * 답글이 달려있는 문의사항은 삭제 불가 처리
     * */
    @ApiOperation(value = "문의 사항을 삭제한다.", response = String.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "문의 사항 삭제 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @DeleteMapping("/questions/{question_seq}")
    public ResponseEntity<Response> updateInquiry(@PathVariable("question_seq") Long questionSeq) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        inquiryService.hiddenInquiry(questionSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("문의 사항 숨기처리(삭제) 완료");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 문의 사항 답변 등록하기
     * */
    @ApiOperation(value = "답변을 작성한다.", response = String.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "답변 작성 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @PostMapping("/answers")
    public ResponseEntity<Response> insertAnswer(@RequestBody AnswerDto answerDto) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        inquiryService.insertAnswer(answerDto);
        response.setStatus(StatusEnum.OK);
        response.setMessage("답변이 작성이 완료되었습니다.");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 문의 사항 답변 수정하기
     * */
    @ApiOperation(value = "답변을 수정한다.", response = String.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "답변 수정 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @PutMapping("/answers")
    public ResponseEntity<Response> updateAnswer(@RequestBody AnswerDto answerDto) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        inquiryService.updateAnswer(answerDto);
        response.setStatus(StatusEnum.OK);
        response.setMessage("답변 수정 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

    /*
     * 문의 사항 답변 삭제하기
     * */
    @ApiOperation(value = "답변을 삭제한다.", response = String.class)
    @ApiResponses({
            @ApiResponse(code= 200, message = "답변 삭제 성공"),
            @ApiResponse(code= 404, message = "page not found")})
    @DeleteMapping("/answers/{question_seq}")
    public ResponseEntity<Response> deleteAnswer(@PathVariable("question_seq") Long questionSeq) {
        Response response = new Response();
        HttpHeaders httpHeaders = new HttpHeaders();
        inquiryService.deleteAnswer(questionSeq);
        response.setStatus(StatusEnum.OK);
        response.setMessage("답변 삭제 성공");
        return new ResponseEntity<Response>(response, httpHeaders, HttpStatus.OK);
    }

}


