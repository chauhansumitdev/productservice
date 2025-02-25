package com.example.productservice.customexception;

import com.example.productservice.dto.ResponseAuth;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Handler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseAuth> response(Exception e){
        ResponseAuth responseAuth = new ResponseAuth();
        responseAuth.setMessage(e.getMessage());
        return new ResponseEntity<>(responseAuth, HttpStatus.UNAUTHORIZED);
    }


}
