package com.milkit.app.domain.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.milkit.app.common.JsonPrinter;
import com.milkit.app.domain.notice.service.NoticeServiceImpl;
import com.milkit.app.util.PrintUtil;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootTest
class NoticeServiceTests {

	private static final Logger logger  = LoggerFactory.getLogger(NoticeServiceTests.class);

	@Autowired
    private NoticeServiceImpl noticeServie;



	@Test
	public void Notice_테스트() throws Exception {
		Long id = insert(sequence(), "title1", "contents1", "kim");
		assertTrue(id > 0);
		
		Notice notice = noticeServie.select(id);
		assertTrue(notice != null);
		
		update(id, "title2", "contents2", "kim");
		Notice updateNotice = noticeServie.select(id);
		assertTrue(updateNotice.getTitle().equals("title2"));

		delete(id, "test");
		Notice deleteNotice = noticeServie.select(id);
		assertTrue(deleteNotice.getUseYN().equals("N"));
		
		List<Notice> list = selectAll(0, 100);
		assertTrue(list.size() == 0);

		전체조회_테스트();
	}


	
	@Test
	public void 전체조회_테스트() throws Exception {

		등록_테스트();
		등록_테스트();
		등록_테스트();
		
		List<Notice> list = selectAll(0, 2);
		
		logger.debug("list3:"+list);

		assertTrue(list.size() == 2);

	}

	@Test
	public void 단일조회_테스트() throws Exception {
		Long id = insert(sequence(), "title1", "contents1", "kim");
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice != null);
	}

	@Test
	public void 등록_테스트() throws Exception {
		Long id = insert(sequence(), "title1", "contents1", "kim");

		assertTrue(id > 0);
	}
	
	@Test
	public void 갱신_테스트() throws Exception {
		Long id = insert(sequence(), "title1", "contents1", "kim");
		
		update(id, "title2", "contents2", "kim");
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice.getTitle().equals("title2"));
	}
	
	@Test
	public void 삭제_테스트() throws Exception {
		Long id = insert(sequence(), "title1", "contents1", "kim");
		delete(id, "test");
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice.getUseYN().equals("N"));
	}

	
	@Test
	public void sequence조회_테스트() throws Exception {
		Long seq = sequence();

		assertTrue(seq > 0);

		logger.debug("seq:"+seq);
	}

	
	
	private Long sequence() throws Exception {
		return noticeServie.selectSeq();
	}
	
	private List<Notice> selectAll(int page, int size) throws Exception {
		Pageable firstPageWithTwoElements = PageRequest.of(page, size);
		Page<Notice> pages = noticeServie.selectAll("Y", firstPageWithTwoElements);

		return pages.getContent();
	}
	
	private Long insert(Long id, String title, String content, String instUser) throws Exception {
		Notice notice = new Notice(id, title, content, instUser);
		return noticeServie.insert(notice);
	}
	
	private void update(Long id, String title, String content, String instUser) throws Exception {
		Notice notice = new Notice(id, title, content, instUser);
		
		noticeServie.update(notice);
	}
	
	private void delete(Long id, String updUser) throws Exception {
		noticeServie.disable(id, updUser);
	}

}
