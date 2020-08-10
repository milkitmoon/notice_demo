package com.milkit.app.api.userinfo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.milkit.app.common.exception.handler.RestResponseEntityExceptionHandler;
import com.milkit.app.domain.userinfo.UserInfo;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
public class UserInfoController {

    @Autowired
    private UserInfoServiceImpl memberServie;

    @RequestMapping("/api/userinfo")
    public List<UserInfo> userinfo(@RequestParam(value="name", defaultValue="World") String name) throws Exception {
        return memberServie.selectAll();
    }
    
}