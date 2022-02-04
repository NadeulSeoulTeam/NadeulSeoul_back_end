package com.alzal.nadeulseoulbackend.global.exception;

import com.alzal.nadeulseoulbackend.domain.tag.exception.TagCustomException;
import com.alzal.nadeulseoulbackend.global.common.ErrorResponse;
import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.nio.charset.Charset;

// TODO:
//  각 Domain에서 처리할 exception 정리 필요
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ErrorStatusEnum.BAD_REQUEST);
        errorResponse.setMessage(e.getClass().getName());
        errorResponse.setData("");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity noHandlerFoundException(NoHandlerFoundException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ErrorStatusEnum.BAD_REQUEST);
        errorResponse.setMessage(e.getClass().getName());
        errorResponse.setData("핸들러 없음");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = TagCustomException.class)
    public ResponseEntity customException(TagCustomException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(e.getErrorStatusEnum());
        errorResponse.setMessage("예외 발생");
        errorResponse.setData(e.getClass().getName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
