package com.back.travelit.common.exception.advice;

import com.back.travelit.common.dto.ErrorResponse;
import com.back.travelit.common.exception.BaseException;
import com.back.travelit.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * BaseException 또는 BaseException을 상속받는 예외를 처리하는 메서드
     * API(ajax)요청에 대하여 예외가 발생할 경우 해당 예외를 발생시켜서 처리
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BaseException e) {
        ErrorCode errorCode = e.getErrorCode();
        ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

}
