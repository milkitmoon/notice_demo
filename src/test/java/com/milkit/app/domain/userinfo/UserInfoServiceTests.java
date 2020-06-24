package com.milkit.app.domain.userinfo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.milkit.app.domain.userinfo.UserInfo;
import com.milkit.app.domain.userinfo.service.UserInfoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class UserInfoServiceTests {

	private static final Logger logger  = LoggerFactory.getLogger(UserInfoServiceTests.class);

	@Autowired
    private UserInfoServiceImpl userInfoServie;


	@Test
	public void UserInfo_ID_select_TEST() throws Exception {

		UserInfo_insert_TEST();

		UserInfo userInfo = userInfoServie.select(1l);

		logger.debug(userInfo.toString());
	}
	
	@Test
	public void UserInfo_selectUserID_TEST() throws Exception {

		UserInfo_insert_TEST();

		UserInfo userInfo = userInfoServie.select("test");

		logger.debug(userInfo.toString());
	}




	@Test
	public void UserInfo_selectAll_TEST() throws Exception {

		List<UserInfo> list = userInfoServie.selectAll();

		logger.debug("size:"+list.size());
	}

	@Test
	public void UserInfo_insert_TEST() throws Exception {

//		String password = "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08";	//sha256
		String password = "$2a$10$U.0zo/wtkAiqBfxoaeiHmuzUT7pjf5hJmmn/Hg5iFyuEMNUvA.FpW";		//bcrypt
		
		UserInfo userInfo = new UserInfo("test", "");
		Long id = userInfoServie.insert(userInfo);

		logger.debug(id.toString());
	}

}
