package com.milkit.app.domain.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.milkit.app.common.JsonPrinter;
import com.milkit.app.domain.notice.service.NoticeAttachServiceImpl;
import com.milkit.app.util.PrintUtil;

import org.junit.jupiter.api.Test;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class NoticeAttachServiceTests {

	private static final Logger logger  = LoggerFactory.getLogger(NoticeAttachServiceTests.class);

	@Autowired
    private NoticeAttachServiceImpl noticeAttachServie;


	@Test
	public void NoticeAttach테스트() throws Exception {
		Long id = insert(1l, "테스트.jpg", "Y");
		assertTrue(id > 0);

		delete(id, "test");
		List<NoticeAttach> list = selectAll(1l, 0, 10);
		assertTrue(list.size() == 0);

		NoticeAttach전체조회테스트();
	}


/**/
	@Test
	public void NoticeAttach전체조회테스트() throws Exception {
		insert(1l, "테스트.jpg", "Y");
		insert(1l, "테스트2.jpg", "Y");
		insert(1l, "테스트3.jpg", "Y");
		
		List<NoticeAttach> list = selectAll(1l, 0, 2);

		assertTrue(list.size() == 2);
	}

	@Test
	public void NoticeAttach등록테스트() throws Exception {
		Long id = insert(1l, "테스트.jpg", "Y");

		assertTrue(id > 0);
	}

	
	@Test
	public void NoticeAttach삭제테스트() throws Exception {
		Long id = insert(1l, "테스트.jpg", "Y");
		delete(id, "test");
		
		List<NoticeAttach> list = selectAll(1l, 0, 10);
		
		assertTrue(list.size() == 0);
	}
	
	
	
	private List<NoticeAttach> selectAll(Long noticeID, int page, int size) throws Exception {
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		Page<NoticeAttach> pages = noticeAttachServie.selectAll(noticeID, "Y", firstPageWithTwoElements);

		return pages.getContent();
	}
	
	private Long insert(Long noticeID, String filename, String useYN) throws Exception {
		NoticeAttach notice = new NoticeAttach(noticeID, filename, useYN);
		return noticeAttachServie.insert(notice);
	}
	
	private void delete(Long id, String updUser) throws Exception {
		noticeAttachServie.disable(id, updUser);
	}

}
