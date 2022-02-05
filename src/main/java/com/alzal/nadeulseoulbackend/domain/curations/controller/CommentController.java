package com.alzal.nadeulseoulbackend.domain.curations.controller;

import com.alzal.nadeulseoulbackend.domain.curations.dto.CommentDto;
import com.alzal.nadeulseoulbackend.domain.curations.service.CommentService;
import com.alzal.nadeulseoulbackend.global.common.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "CommentController")
@RequestMapping("api/v1/curations/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "댓글 작성", notes = "댓글 작성하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 작성 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PostMapping("")
    public ResponseEntity<Response> insertComment(@RequestBody final CommentDto commentDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        commentService.insertComment(commentDto);
        response.setMessage("댓글 작성이 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 수정", notes = "댓글 수정하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 수정 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @PutMapping("")
    public ResponseEntity<Response> updateComment(@RequestBody final CommentDto commentDto) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        commentService.updateComment(commentDto);
        response.setMessage("댓글 수정이 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

    @ApiOperation(value = "댓글 삭제", notes = "댓글 삭제하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "댓글 삭제 성공"),
            @ApiResponse(code = 404, message = "page not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Response> deleteComment(@PathVariable("id") final Long commentSeq) {
        Response response = new Response();
        HttpHeaders headers = new HttpHeaders();
        commentService.deleteByCommentSeq(commentSeq);
        response.setMessage("댓글 삭제가 완료되었습니다.");
        return new ResponseEntity<>(response, headers, HttpStatus.OK);
    }

}
