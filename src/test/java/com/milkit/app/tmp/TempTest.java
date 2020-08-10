package com.milkit.app.tmp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.milkit.app.common.exception.handler.RestResponseEntityExceptionHandler;
import com.milkit.app.domain.userinfo.UserInfo;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
public class TempTest {

	@Test
	public void 로그_테스트() throws Exception {
		log.debug("로그 테스트");
	}
}
