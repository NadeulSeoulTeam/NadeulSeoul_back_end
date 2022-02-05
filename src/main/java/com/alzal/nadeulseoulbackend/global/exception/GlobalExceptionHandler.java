package com.alzal.nadeulseoulbackend.global.exception;

import com.alzal.nadeulseoulbackend.global.common.ErrorResponse;
import com.alzal.nadeulseoulbackend.global.common.ErrorStatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

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

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity customException(CustomException e){
        ErrorResponse errorResponse = new ErrorResponse();
        ErrorStatusEnum errorStatusEnum = e.getErrorStatusEnum();
        errorResponse.setStatus(errorStatusEnum);
        errorResponse.setMessage(errorStatusEnum.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorStatusEnum.getCode()));
    }

}