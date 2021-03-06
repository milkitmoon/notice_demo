package com.milkit.app.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.milkit.app.domain.userinfo.UserInfo;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

@Component
public class DataLoader implements ApplicationRunner {

    private UserInfoServiceImpl userInfoService;

    @Autowired
    public DataLoader(UserInfoServiceImpl userInfoService) {
        this.userInfoService = userInfoService;
    }

    public void run(ApplicationArguments args) {
		String password = "ecc8515652bb0ef58bcf88e5bc8693352614053a0622ce213f644bcc0ffa0331d16e6b0b02385683";		//sha256
		
		UserInfo userInfo = new UserInfo("admin", password);
		userInfo.setUserNM("관리자");
		userInfo.setAuthRole("ROLE_ADMIN");
		userInfo.setUseYN("Y");
		userInfo.setInstTime(new Date());
		userInfo.setUpdTime(new Date());
		userInfo.setInstUser("admin");
		userInfo.setUpdUser("admin");
		
		UserInfo userInfo2 = new UserInfo("test", password);
		userInfo2.setUserNM("김복돌");
		userInfo2.setAuthRole("ROLE_MEMBER");
		userInfo2.setUseYN("Y");
		userInfo2.setInstTime(new Date());
		userInfo2.setUpdTime(new Date());
		userInfo2.setInstUser("admin");
		userInfo2.setUpdUser("admin");
		
		try {
			userInfoService.insert(userInfo);
			userInfoService.insert(userInfo2);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }


}