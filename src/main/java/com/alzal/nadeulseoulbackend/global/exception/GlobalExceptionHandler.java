package com.alzal.nadeulseoulbackend.global.exception;

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

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.SQLException;

// TODO:
//  각 Domain에서 처리할 exception 정리 필요
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ErrorStatusEnum.BAD_REQUEST);
        errorResponse.setMessage(e.getClass().getName());
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

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity customException(CustomException e){
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorStatusEnum errorStatusEnum = e.getErrorStatusEnum();
        errorResponse.setStatus(errorStatusEnum);
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorStatusEnum.getCode()));
    }

    @ExceptionHandler(value = {IOException.class, SQLException.class})
    public ResponseEntity handleException(Exception e){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(ErrorStatusEnum.BAD_REQUEST);
        errorResponse.setMessage(e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
