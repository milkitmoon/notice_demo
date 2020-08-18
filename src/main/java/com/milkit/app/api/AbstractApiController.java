package com.milkit.app.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.milkit.app.common.response.AnonymouseResult;

import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AbstractApiController {

    public <T> ResponseEntity<T> apiResponse(final AnonymouseResult templateResult) throws Exception {
    	return success(templateResult.body());
    }

    private <T> ResponseEntity<T> success(Object result) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<T>((T)result, headers, HttpStatus.OK);
    }
    
    private <T> ResponseEntity<T> unauthorized(Object result) {
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<T>((T)result, headers, HttpStatus.UNAUTHORIZED);
    }
    
}
