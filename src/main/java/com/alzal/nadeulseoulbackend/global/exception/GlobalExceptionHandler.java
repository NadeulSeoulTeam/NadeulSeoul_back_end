package com.alzal.nadeulseoulbackend.global.exception;

import com.alzal.nadeulseoulbackend.global.common.Response;
import com.alzal.nadeulseoulbackend.global.common.StatusEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity methodArgumentNotValid(MethodArgumentNotValidException e) {
        Response errorResponse = new Response(StatusEnum.BAD_REQUEST, e.getClass().getName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity noHandlerFoundException(NoHandlerFoundException e) {
        Response errorResponse = new Response(StatusEnum.BAD_REQUEST, e.getClass().getName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity noHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        Response errorResponse = new Response(StatusEnum.BAD_REQUEST, e.getClass().getName());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity customException(CustomException e) {
        StatusEnum errorStatusEnum = e.getStatusEnum();
        Response errorResponse = new Response( errorStatusEnum, e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
