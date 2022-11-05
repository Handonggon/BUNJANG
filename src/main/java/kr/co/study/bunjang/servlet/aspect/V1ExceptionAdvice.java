package kr.co.study.bunjang.servlet.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("kr.co.study.bunjang.mvc.controller.v1")
public class V1ExceptionAdvice {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception e) {
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}