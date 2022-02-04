package com.alzal.nadeulseoulbackend.domain.inqury.controller;

import com.alzal.nadeulseoulbackend.domain.inqury.dto.Inqury;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryInfoDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InqurylistDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.Member;
import com.alzal.nadeulseoulbackend.domain.inqury.service.InquryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//http://localhost:8080/vuews/swagger-ui.html
@RestController
@RequestMapping("api/v1/inqury")
@Api(value = "InquryController")
public class InquryController {

    @Autowired
    private InquryService inquryService;

    /*
    * 문의사항 목록 가져오기
    * */
    @ApiOperation(value = "member_seq에 해당하는 문의사항 목록 반환한다.", response = List.class)
    @GetMapping("/questions")
    public ResponseEntity<InqurylistDto> getInquryList() {
        // 사용자 정보 토큰으로 가져옴
        Member member = new Member();
        Long memberSeq = member.getMemberSeq();
        return new ResponseEntity<InqurylistDto>(inquryService.getInquryList(memberSeq), HttpStatus.OK);
    }

    /*
     * 문의사항 내용 가져오기
     * */
    @ApiOperation(value = "question_seq에 해당하는 문의사항 세부내용을 반환한다.", response = Inqury.class)
    @GetMapping("/questions/{question_seq}")
    public ResponseEntity<InquryInfoDto> getInqury(@PathVariable("question_seq") Long questionSeq) {
        return new ResponseEntity<InquryInfoDto>(inquryService.getInqury(questionSeq), HttpStatus.OK);
    }

    /*
    * 문의사항 작성하기
   * */
    @ApiOperation(value = "문의 사항을 저장한다.", response = String.class)
    @PostMapping
    public ResponseEntity<String> saveInqury() {
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

}


