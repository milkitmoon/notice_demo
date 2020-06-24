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
	public void Notice테스트() throws Exception {
		Long id = insert(sequence(), "제목1", "내용1", "kim");
		assertTrue(id > 0);
		
		Notice notice = noticeServie.select(id);
		assertTrue(notice != null);
		
		update(id, "제목2", "내용2", "kim");
		Notice updateNotice = noticeServie.select(id);
		assertTrue(updateNotice.getTitle().equals("제목2"));

		delete(id, "test");
		Notice deleteNotice = noticeServie.select(id);
		assertTrue(deleteNotice.getUseYN().equals("N"));
		
		List<Notice> list = selectAll(0, 100);
		assertTrue(list.size() == 0);

		Notice전체조회테스트();
	}


	
	@Test
	public void Notice전체조회테스트() throws Exception {

		Notice등록테스트();
		Notice등록테스트();
		Notice등록테스트();
		
		List<Notice> list = selectAll(0, 2);

		assertTrue(list.size() == 2);

	}

	@Test
	public void Notice조회테스트() throws Exception {
		Long id = insert(sequence(), "제목1", "내용1", "kim");
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice != null);
	}

	@Test
	public void Notice등록테스트() throws Exception {
		Long id = insert(sequence(), "제목1", "내용1", "kim");

		assertTrue(id > 0);
	}
	
	@Test
	public void Notice수정테스트() throws Exception {
		Long id = insert(sequence(), "제목1", "내용1", "kim");
		
		update(id, "제목2", "내용2", "kim");
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice.getTitle().equals("제목2"));
	}
	
	@Test
	public void Notice삭제테스트() throws Exception {
		Long id = insert(sequence(), "제목1", "내용1", "kim");
		delete(id, "test");
		
		Notice notice = noticeServie.select(id);

		assertTrue(notice.getUseYN().equals("N"));
	}

	
	@Test
	public void Notice_Seq조회테스트() throws Exception {
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
