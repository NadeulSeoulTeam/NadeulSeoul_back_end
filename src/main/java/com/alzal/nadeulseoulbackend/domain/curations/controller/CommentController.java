package com.alzal.nadeulseoulbackend.domain.curations.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CommentRequestDto;
import com.alzal.nadeulseoulbackend.domain.curations.dto.CommentResponseDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.CommentService;
import com.alzal.nadeulseoulbackend.domain.users.service.UserInfoService;
import com.alzal.nadeulseoulbackend.global.common.Response;
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

import java.util.List;

@RestController
@Api(value = "CommentController")
@RequestMapping("api/v1/curations/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserInfoService userInfoService;

    @ApiOperation(value = "댓글 목록", notes = "댓글 목록 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 목록 불러오기 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/{id}/list")
    public ResponseEntity<Response> getCommentList(@PathVariable("id") final Long curationSeq) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        List<CommentResponseDto> commentResponseDtoList = commentService.getCommentList(curationSeq);
        response.setMessage("댓글 목록을 불러왔습니다.");
        response.setData(commentResponseDtoList);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 목록", notes = "댓글 목록 불러오기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 목록 불러오기 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Response> getCommentListPage(@PathVariable("id") final Long curationSeq, @PageableDefault(page = 0, size = 10, sort = "date", direction = Sort.Direction.ASC) Pageable pageable) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();

        Page<CommentResponseDto> commentResponseDtoPage = commentService.getCommentListByPage(curationSeq, pageable);
        response.setMessage("댓글 목록을 불러왔습니다.");
        response.setData(commentResponseDtoPage);
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 작성", notes = "댓글 작성하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 작성 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("/auth")
    public ResponseEntity<Response> insertComment(@RequestBody final CommentRequestDto commentRequestDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        commentService.insertComment(commentRequestDto, userInfoService.getId());
        response.setMessage("댓글 작성이 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }


    @ApiOperation(value = "댓글 수정", notes = "댓글 수정하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 수정 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PutMapping("/auth")
    public ResponseEntity<Response> updateComment(@RequestBody final CommentRequestDto commentRequestDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        commentService.updateComment(commentRequestDto, userInfoService.getId());
        response.setMessage("댓글 수정이 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 삭제 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @DeleteMapping("/{id}/auth")
    public ResponseEntity<Response> deleteComment(@PathVariable("id") final Long commentSeq) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        commentService.deleteByCommentSeq(commentSeq, userInfoService.getId());
        response.setMessage("댓글 삭제가 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
