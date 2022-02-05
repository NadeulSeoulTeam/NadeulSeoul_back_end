package com.alzal.nadeulseoulbackend.domain.inqury.controller;

import com.alzal.nadeulseoulbackend.domain.inqury.dto.Inqury;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryInfoDto;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.InquryDtoList;
import com.alzal.nadeulseoulbackend.domain.inqury.dto.Member;
import com.alzal.nadeulseoulbackend.domain.inqury.service.InquryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

// http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("api/v1/profiles/questions")
@Api(value = "InquryController")
public class InquryController {

    @Autowired
    private InquryService inquryService;

    /*
    * 문의사항 목록 가져오기
    * */
    @ApiOperation(value = "member_seq에 해당하는 문의사항 목록 반환한다.", response = List.class)
    @ApiResponse(code = 200, message = "목록 가져오기 성공")
    @GetMapping
    public ResponseEntity<InquryDtoList> getInquryList() {
        // 사용자 정보 토큰으로 가져옴
        Member member = new Member();
        Long memberSeq = member.getMemberSeq();
        return new ResponseEntity<InquryDtoList>(inquryService.getInquryList(memberSeq), HttpStatus.OK);
    }

    /*
     * 문의사항 내용 가져오기
     * */
    @ApiOperation(value = "question_seq에 해당하는 문의사항 세부내용을 반환한다.", response = Inqury.class)
    @ApiResponse(code = 200, message = "문의 사항 가져오기 성공")
    @GetMapping("/{question_seq}")
    public ResponseEntity<InquryInfoDto> getInqury(@PathVariable("question_seq") Long questionSeq) {
        return new ResponseEntity<InquryInfoDto>(inquryService.getInqury(questionSeq), HttpStatus.OK);
    }

    /*
    * 문의사항 작성하기
   * */
    @ApiOperation(value = "문의 사항을 저장한다.", response = String.class)
    @ApiResponse(code = 200, message = "문의 사항 작성 성공")
    @PostMapping
    public ResponseEntity<String> insertInqury(@RequestBody InquryInfoDto inquryInfoDto) {
        int result = inquryService.insertInqury(inquryInfoDto);

        if(result == 0){
            return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    /*
     * 문의사항 수정하기
     * */
    @ApiOperation(value = "문의 사항을 수정한다.", response = String.class)
    @ApiResponse(code = 200, message = "문의 사항 수정 성공")
    @PutMapping("/{question_seq}")
    public ResponseEntity<String> updateInqury(@PathVariable("question_seq") Long questionSeq, @RequestBody InquryInfoDto inquryInfoDto) {
        int result = inquryService.updateInqury(questionSeq, inquryInfoDto);

        if(result == 0) {
            return new ResponseEntity<String>("FAIL", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

    /*
     * 문의사항 삭제하기 (실제 db에서 삭제하지 않고 숨김처리)
     * 답글이 달려있는 문의사항은 삭제 불가 처리
     * */
    @ApiOperation(value = "문의 사항을 삭제한다.", response = String.class)
    @ApiResponse(code = 200, message = "문의 사항 삭제 성공")
    @DeleteMapping("/{question_seq}")
    public ResponseEntity<String> updateInqury(@PathVariable("question_seq") Long questionSeq) {
        int result = inquryService.hiddenInqury(questionSeq);
        String str = "";

        if(result == 0) { // 해당 번호의 문의 사항이 존재하지 않음
            str = "해당 문의 사항이 존재하지 않습니다.";
        }else if(result == 1){
            str = "답글이 있는 문의 사항은 삭제할 수 없습니다.";
        }
        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }

}


